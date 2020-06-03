package cn.bonusli.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import cn.bonusli.batch.entity.TestData;

/**
 * @Author lxj
 */
@Component
public class TestDataTransformItemProcessor implements ItemProcessor<TestData, TestData> {

    @Override
    public TestData process(TestData item) throws Exception {
        // field1值拼接 hello
        item.setField1(item.getField1() + " hello");
        return item;
    }
}
