package com.zhx.quartz.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class QuartzLog implements Serializable {

    private Long id;

    /** 任务名称 */
    private String jobName;

    /** Bean名称 */
    private String beanName;

    /** 方法名称 */
    private String methodName;

    /** 参数 */
    private String params;

    /** cron表达式 */
    private String cronExpression;

    /** 状态 */
    private Boolean isSuccess;

    /** 异常详细 */
    private String exceptionDetail;

    /** 耗时（毫秒） */
    private Long time;

    /** 创建日期 */
    private Timestamp createTime;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getJobName() {
        return jobName;
    }
    
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    
    public String getBeanName() {
        return beanName;
    }
    
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
    
    public String getMethodName() {
        return methodName;
    }
    
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
    public String getParams() {
        return params;
    }
    
    public void setParams(String params) {
        this.params = params;
    }
    
    public String getCronExpression() {
        return cronExpression;
    }
    
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
    
    public Boolean getIsSuccess() {
        return isSuccess;
    }
    
    public void setIsSuccess(Boolean success) {
        isSuccess = success;
    }
    
    public String getExceptionDetail() {
        return exceptionDetail;
    }
    
    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }
    
    public Long getTime() {
        return time;
    }
    
    public void setTime(Long time) {
        this.time = time;
    }
    
    public Timestamp getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
