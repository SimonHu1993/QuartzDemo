package com.zhx.quartz.mapper;

import com.zhx.quartz.domain.QuartzLog;

import java.util.List;
import java.util.Map;

public interface QuartzLogMapper {
    int insert(QuartzLog quartzLog);
    
    List<QuartzLog> selectByPrimaryKey(Map param);
}