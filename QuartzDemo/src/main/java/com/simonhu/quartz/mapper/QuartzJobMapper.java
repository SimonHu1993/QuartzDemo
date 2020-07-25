package com.simonhu.quartz.mapper;

import com.simonhu.quartz.domain.QuartzJob;

import java.util.List;
import java.util.Map;

public interface QuartzJobMapper {
    List<QuartzJob> queryJob(Map param);
    
    int updateJobById(QuartzJob job);
    
    int insertSelective(QuartzJob job);
}
