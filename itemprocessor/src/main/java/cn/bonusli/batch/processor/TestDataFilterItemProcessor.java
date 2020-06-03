package cn.bonusli.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import cn.bonusli.batch.entity.TestData;

/**
 * @Author lxj
 */
@Component
public class TestDataFilterItemProcessor implements ItemProcessor<TestData, TestData> {

    @Override
    public TestData process(TestData testData) throws Exception {
        return "".equals(testData.getField3()) ? null : testData;
    }
}
