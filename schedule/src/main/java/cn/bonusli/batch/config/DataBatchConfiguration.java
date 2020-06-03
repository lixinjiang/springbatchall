package cn.bonusli.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author lxj
 */
@Configuration
@EnableBatchProcessing
@Slf4j
public class DataBatchConfiguration {
    /**
     * job的调度
     */
    @Autowired
    public JobLauncher jobLauncher;

    public void run(String jobName, JobParameters param) {
        try {
            log.info(param.getParameters().toString());
            //通过jobLauncher执行job
            JobExecution execution = jobLauncher.run(SpringApplicationContextUtil.getBean(jobName, Job.class), param);
            log.info("Exit Status : " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
