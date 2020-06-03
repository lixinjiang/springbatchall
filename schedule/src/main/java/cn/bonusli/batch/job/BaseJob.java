package cn.bonusli.batch.job;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import cn.bonusli.batch.listener.JobListener;

/**
 * @Author lxj
 */
public abstract class BaseJob {
    /**
     * 用于构建JOB
     */
    @Resource
    protected JobBuilderFactory jobBuilderFactory;

    /**
     * 用于构建Step
     */
    @Resource
    protected StepBuilderFactory stepBuilderFactory;

    /**
     * 简单的JOB listener
     */
    @Resource
    protected JobListener jobListener;

    @Resource
    protected ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 注入实例化Factory 访问数据
     */
    @Resource
    protected EntityManagerFactory emf;
}
