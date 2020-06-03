package cn.bonusli.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import cn.bonusli.batch.config.SpringApplicationContextUtil;

/**
 * @Author lxj
 */
@SpringBootApplication
@EnableScheduling
public class SpringBatchScheduleApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBatchScheduleApplication.class, args);
        SpringApplicationContextUtil.setApplicationContext(run);
    }
}
