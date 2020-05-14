package com.zhx.quartz.utils;

import com.zhx.quartz.domain.QuartzJob;
import com.zhx.quartz.mapper.QuartzJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: SimonHu
 * @Date: 2020/5/14 8:49
 * @Description:
 */
@Component
public class InitQuartzJob {
    private static final Logger logger = LoggerFactory.getLogger(InitQuartzJob.class);
    @Autowired
    private QuartzManage quartzManage;
    @Autowired
    private QuartzJobMapper quartzJobMapper;
    
    /**
     * @return void
     * @Description:初始化定时任务 @PostConstruct注解启动时执行该方法
     * @Author:SimonHu
     * @Date: 2020/5/14 8:59
     */
    @PostConstruct
    public void init() {
        // 这里从数据库中获取任务信息数据
        Map map = new HashMap(16);
        //获取未暂停的任务
        map.put("isPause","0");
        List<QuartzJob> quartzJobs = quartzJobMapper.queryJob(map);
        for (QuartzJob job : quartzJobs) {
            logger.info("-----init job----" + job.getJobName());
            quartzManage.addJob(job);
        }
    }
}
