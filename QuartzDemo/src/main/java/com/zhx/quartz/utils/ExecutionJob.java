package com.zhx.quartz.utils;

import com.zhx.quartz.domain.QuartzJob;
import com.zhx.quartz.domain.QuartzLog;
import com.zhx.quartz.service.QuartzJobService;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 参考人人开源，https://gitee.com/renrenio/renren-security
 *
 * @author /
 * @date 2019-01-07
 */
@Async
public class ExecutionJob extends QuartzJobBean {
    @Autowired
    private QuartzJobService quartzJobService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 该处仅供参考
     */
    private final static ThreadPoolExecutor EXECUTOR = ThreadPoolExecutorUtil.getPoll();
    
    @Override
    @SuppressWarnings("unchecked")
    protected void executeInternal(JobExecutionContext context) {
        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        // 获取spring bean
        QuartzJobService quartzJobService = SpringContextHolder.getBean(QuartzJobService.class);
        QuartzLog log = new QuartzLog();
        log.setJobName(quartzJob.getJobName());
        log.setBeanName(quartzJob.getBeanName());
        log.setMethodName(quartzJob.getMethodName());
        log.setParams(quartzJob.getParams());
        long startTime = System.currentTimeMillis();
        log.setCronExpression(quartzJob.getCronExpression());
        try {
            // 执行任务
            logger.info("任务准备执行，任务名称：{}", quartzJob.getJobName());
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
                    quartzJob.getParams());
            Future<?> future = EXECUTOR.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态
            log.setIsSuccess(true);
            logger.info("任务执行完毕，任务名称：{} 总共耗时：{} 毫秒", quartzJob.getJobName(), times);
            logger.info("-----------------Quartz线程 活动情况-------------------------------------");
            int queueSize = EXECUTOR.getQueue().size();
            logger.info("当前排队线程数：" + queueSize);
    
            int activeCount = EXECUTOR.getActiveCount();
            logger.info("当前活动线程数：" + activeCount);
    
            long completedTaskCount = EXECUTOR.getCompletedTaskCount();
            logger.info("执行完成线程数：" + completedTaskCount);
    
            long taskCount = EXECUTOR.getTaskCount();
            logger.info("总线程数：" + taskCount);
            logger.info("-----------------Quartz线程 活动情况-------------------------------------");
        } catch (Exception e) {
            logger.error("任务执行失败，任务名称：{}" + quartzJob.getJobName(), e);
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态 0：成功 1：失败
            log.setIsSuccess(false);
            log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            quartzJob.setIsPause(false);
            //更新状态
            quartzJobService.updateIsPause(quartzJob.getId());
        } finally {
            logger.info("------------记录 调度 日志--------");
            quartzJobService.inserLog(log);
        }
    }
}
