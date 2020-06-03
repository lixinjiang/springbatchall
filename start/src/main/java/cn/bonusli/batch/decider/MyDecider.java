package cn.bonusli.batch.decider;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

/**
 * 判断当前是否工作日，返回指定的状态作为判断流转依据
 *
 * @Author lxj
 */
@Component
public class MyDecider implements JobExecutionDecider {

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return new FlowExecutionStatus("weekend");
        } else {
            return new FlowExecutionStatus("workingDay");
        }
    }
}
