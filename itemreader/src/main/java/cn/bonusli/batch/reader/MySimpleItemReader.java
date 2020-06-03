package cn.bonusli.batch.reader;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;

/**
 * @Author lxj
 */
public class MySimpleItemReader implements ItemReader<String> {

    private Iterator<String> iterator;

    public MySimpleItemReader(List<String> data) {
        this.iterator = data.iterator();
    }

    @Override
    public String read() {
        return iterator.hasNext() ? iterator.next() : null;
    }
}
