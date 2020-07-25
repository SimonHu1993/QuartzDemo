package com.simonhu.quartz.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
public class QuartzJob implements Serializable {
    public static final String JOB_KEY = "JOB_KEY";
    private Long id;
    /**
     * 定时器名称
     */
    private String jobName;
    /**
     * Bean名称
     */
    private String beanName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数
     */
    private String params;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 状态
     */
    private Boolean isPause = false;
    /**
     * 备注
     */
    private String remark;
    private Timestamp createTime;
    private int status;
    
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
    
    public Boolean getIsPause() {
        return isPause;
    }
    
    public void setIsPause(Boolean pause) {
        isPause = pause;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Timestamp getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
}
