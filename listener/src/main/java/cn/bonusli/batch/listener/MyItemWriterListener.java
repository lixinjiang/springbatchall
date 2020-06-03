package cn.bonusli.batch.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

/**
 * @Author lxj
 */
@Component
public class MyItemWriterListener implements ItemWriteListener<String> {
    @Override
    public void beforeWrite(List<? extends String> items) {
        System.out.println("before write: " + items);
    }

    @Override
    public void afterWrite(List<? extends String> items) {
        System.out.println("after write: " + items);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends String> items) {
        System.out.println("on write error: " + items + " , error message: " + exception.getMessage());
    }
}
