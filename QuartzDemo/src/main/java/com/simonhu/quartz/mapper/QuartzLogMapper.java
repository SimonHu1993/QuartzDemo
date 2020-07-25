package com.simonhu.quartz.mapper;

import com.simonhu.quartz.domain.QuartzLog;

import java.util.List;
import java.util.Map;

public interface QuartzLogMapper {
    int insert(QuartzLog quartzLog);
    
    List<QuartzLog> selectByPrimaryKey(Map param);
}
