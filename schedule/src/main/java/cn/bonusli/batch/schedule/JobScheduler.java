package cn.bonusli.batch.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.bonusli.batch.config.DataBatchConfiguration;
import cn.bonusli.batch.config.JobNameConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author lxj
 */
@Component
@Slf4j
public class JobScheduler {
    @Autowired
    private DataBatchConfiguration billBatchConfig;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 30秒执行一次
     **/
    @Scheduled(cron = "0/30 * * * * ? ")
    public void fixedTimePerDayBillBatch() {
        log.info("schedule-job begin {}", dateFormat.format(new Date()));
        JobParameters param = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
        String jobName = JobNameConstants.BILL_JOB_NAME;
        billBatchConfig.run(jobName, param);
        log.info("schedule-job end {}", dateFormat.format(new Date()));
    }
}
