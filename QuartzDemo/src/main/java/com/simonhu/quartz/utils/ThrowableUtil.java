package com.simonhu.quartz.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具 2019-01-06
 *
 * @author Zheng Jie
 */
public class ThrowableUtil {
    /**
     * 获取堆栈信息--分行显示
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
    /**
      * @Description:获取堆栈信息--单行显示
      * @Author:SimonHu
      * @Date: 2020/5/14 15:59
      * @param throwable
      * @return java.lang.String
      */
    public static String getStackTrace2(Throwable throwable) {
        StringWriter sw = new StringWriter();
        StackTraceElement[] error = throwable.getStackTrace();
        for (StackTraceElement stackTraceElement : error) {
            sw.append(stackTraceElement.toString());
        }
        return sw.toString();
    }
}
