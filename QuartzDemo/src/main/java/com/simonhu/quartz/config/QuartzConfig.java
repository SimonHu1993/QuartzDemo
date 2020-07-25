package com.simonhu.quartz.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务配置
 *
 * @author /
 * @date 2019-01-07
 */
@Configuration
public class QuartzConfig {
    /**
     * 注入scheduler到spring
     *
     * @param quartzJobFactory /
     * @return Scheduler
     * @throws /
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler(QuartzJobFactory quartzJobFactory) throws Exception {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(quartzJobFactory);
        factoryBean.afterPropertiesSet();
        Scheduler scheduler = factoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }
}
