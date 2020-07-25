package com.simonhu.quartz.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 用于获取自定义线程池
 *
 * @author Zheng Jie
 * @date 2019年10月31日18:16:47
 */
public class ThreadPoolExecutorUtil {
    public static ThreadPoolExecutor getPoll() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                20,
                100,
                300,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(50),
                new TheadFactoryName()
        );
        return threadPoolExecutor;
    }
}
