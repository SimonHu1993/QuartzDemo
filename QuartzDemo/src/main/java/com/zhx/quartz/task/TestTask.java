package com.zhx.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 测试用
 * @author Zheng Jie
 * @date 2019-01-08
 */
@Component
public class TestTask {
    private static final Logger log = LoggerFactory.getLogger(TestTask.class);

    public void run(){
        log.info("执行成功");
    }

    public void run1(String str){
        log.info("执行成功，参数为： {}" + str);
    }
}
