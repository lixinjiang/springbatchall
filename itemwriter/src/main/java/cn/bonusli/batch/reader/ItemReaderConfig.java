package cn.bonusli.batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import cn.bonusli.batch.entity.TestData;

/**
 * @Author lxj
 */
@Component
public class ItemReaderConfig {

    @Bean
    public ListItemReader<TestData> simpleReader() {
        List<TestData> data = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String v = String.valueOf(i);
            TestData testData = new TestData();
            testData.setId(i);
            testData.setField1(String.format("%s%s", v, "1"));
            testData.setField2(String.format("%s%s", v, "2"));
            testData.setField3(String.format("%s%s", v, "3"));
            data.add(testData);
        }
        return new ListItemReader<>(data);
    }
}
