package cn.bonusli.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import cn.bonusli.batch.entity.TestData;

/**
 * @Author lxj
 */
@Component
public class MultiFileItemReaderDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job multiFileItemReaderJob() {
        return jobBuilderFactory.get("multiFileItemReaderJob")
                .start(step())
                .build();
    }

    private Step step() {
        return stepBuilderFactory.get("step")
                .<TestData, TestData>chunk(2)
                .reader(multiFileItemReader())
                .writer(list -> list.forEach(System.out::println))
                .build();
    }

    private ItemReader<TestData> multiFileItemReader() {
        MultiResourceItemReader<TestData> reader = new MultiResourceItemReader<>();
        // 设置文件读取代理，方法可以使用前面文件读取中的例子
        reader.setDelegate(fileItemReader());

        Resource[] resources = new Resource[] {
                new ClassPathResource("file1"),
                new ClassPathResource("file2")
        };

        // 设置多文件源
        reader.setResources(resources);
        return reader;
    }

    private FlatFileItemReader<TestData> fileItemReader() {
        FlatFileItemReader<TestData> reader = new FlatFileItemReader<>();
        // 忽略第一行
        reader.setLinesToSkip(1);

        // AbstractLineTokenizer的三个实现类之一，以固定分隔符处理行数据读取,
        // 使用默认构造器的时候，使用逗号作为分隔符，也可以通过有参构造器来指定分隔符
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        // 设置属姓名，类似于表头
        tokenizer.setNames("id", "field1", "field2", "field3");
        // 将每行数据转换为TestData对象
        DefaultLineMapper<TestData> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);
        // 设置映射方式
        mapper.setFieldSetMapper(fieldSet -> {
            TestData data = new TestData();
            data.setId(fieldSet.readInt("id"));
            data.setField1(fieldSet.readString("field1"));
            data.setField2(fieldSet.readString("field2"));
            data.setField3(fieldSet.readString("field3"));
            return data;
        });

        reader.setLineMapper(mapper);
        return reader;
    }
}
