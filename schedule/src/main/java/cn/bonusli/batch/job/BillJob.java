package cn.bonusli.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import cn.bonusli.batch.config.JobNameConstants;
import cn.bonusli.batch.model.Access;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author lxj
 */
@Slf4j
@Component
public class BillJob extends BaseJob {

    @Bean(name = JobNameConstants.BILL_JOB_NAME)
    public Job billJob() {
        return super.jobBuilderFactory.get(JobNameConstants.BILL_JOB_NAME).
                incrementer(new RunIdIncrementer()).
                start(handleDataStep()).
                listener(jobListener).
                build();
    }

    /**
     * 一个简单基础的Step主要分为三个部分
     * ItemReader : 用于读取数据
     * ItemProcessor : 用于处理数据
     * ItemWriter : 用于写数据
     */

    private Step handleDataStep() {
        return stepBuilderFactory.get("getData")
                // <输入,输出> 。chunk通俗的讲类似于SQL的commit; 这里表示处理(processor)100条后写入(writer)一次。
                .<Access, Access>chunk(100)
                //捕捉到异常就重试,重试100次还是异常,JOB就停止并标志失败
                .faultTolerant()
                .retryLimit(3)
                .retry(Exception.class)
                .skipLimit(100)
                .skip(Exception.class)
                //指定ItemReader
                .reader(getDataReader())
                //指定ItemProcessor
                .processor(getDataProcessor())
                //指定ItemWriter
                .writer(getDataWriter())
                .taskExecutor(threadPoolTaskExecutor)
                .throttleLimit(1)
                .build();
    }

    private ItemReader<? extends Access> getDataReader() {
        //读取数据,这里可以用JPA,JDBC,JMS 等方式 读入数据
        JpaPagingItemReader<Access> reader = new JpaPagingItemReader<>();
        //这里选择JPA方式读数据 一个简单的 native SQL
        String sqlQuery = "SELECT * FROM access";
        try {
            JpaNativeQueryProvider<Access> queryProvider = new JpaNativeQueryProvider<>();
            queryProvider.setSqlQuery(sqlQuery);
            queryProvider.setEntityClass(Access.class);
            queryProvider.afterPropertiesSet();
            reader.setEntityManagerFactory(emf);
            reader.setPageSize(3);
            reader.setQueryProvider(queryProvider);
            reader.afterPropertiesSet();
            // 所有ItemReader和ItemWriter实现都会在ExecutionContext提交之前将其当前状态存储在其中,
            // 如果不希望这样做,可以设置setSaveState(false)
            reader.setSaveState(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reader;
    }

    private ItemProcessor<Access, Access> getDataProcessor() {
        return access -> {
            // 模拟  假装处理数据,这里处理就是打印一下
            log.info("processor data : " + access.toString());
            return access;
        };
    }

    private ItemWriter<Access> getDataWriter() {
        return list -> {
            for (Access access : list) {
                //模拟 假装写数据 ,这里写真正写入数据的逻辑
                log.info("write data : " + access);
            }
        };
    }
}
