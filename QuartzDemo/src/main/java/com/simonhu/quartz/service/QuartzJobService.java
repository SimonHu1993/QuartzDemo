package com.simonhu.quartz.service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.simonhu.quartz.domain.QuartzJob;
import com.simonhu.quartz.domain.QuartzLog;
import com.simonhu.quartz.mapper.QuartzJobMapper;
import com.simonhu.quartz.mapper.QuartzLogMapper;
import com.simonhu.quartz.utils.QuartzManage;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Service
public class QuartzJobService extends BaseService {
    @Autowired
    private QuartzLogMapper quartzLogMapper;
    @Autowired
    private QuartzManage quartzManage;
    @Autowired
    private QuartzJobMapper quartzJobMapper;
    
    /**
     * @param page
     * @param pageSize
     * @return java.util.Map
     * @Description:查询所有job
     * @Author:SimonHu
     * @Date: 2020/5/13 17:48
     */
    public Map queryAll(int page, int pageSize) throws Exception {
        Map parmap = new HashMap(16);
        PageList<QuartzJob> orderList = (PageList<QuartzJob>) getPageList(QuartzJobMapper.class, "queryJob", parmap, page, pageSize);
        Map orderMap = packageEasyuiResultMapObject(orderList);
        return orderMap;
    }
    
    /**
     * @param page
     * @param pageSize
     * @return java.util.Map
     * @Description:查询日志
     * @Author:SimonHu
     * @Date: 2020/5/14 14:55
     */
    public Map queryAllLog(int page, int pageSize) throws Exception {
        Map parmap = new HashMap(16);
        PageList<QuartzLog> orderList = (PageList<QuartzLog>) getPageList(QuartzLogMapper.class, "selectByPrimaryKey", parmap, page, pageSize);
        Map orderMap = packageEasyuiResultMapObject(orderList);
        return orderMap;
    }
    /**
      * @Description:插入调度日志
      * @Author:SimonHu
      * @Date: 2020/5/14 15:22
      * @param quartzLog
      * @return int
      */
    public int inserLog(QuartzLog quartzLog) {
        return quartzLogMapper.insert(quartzLog);
    }
    
    /**
     * @param id
     * @return java.util.Map
     * @Description:执行job
     * @Author:SimonHu
     * @Date: 2020/5/13 17:48
     */
    public Map execution(Long id) {
        QuartzJob quartzJob = queQuartzJobById(id);
        quartzManage.runJobNow(quartzJob);
        Map map = new HashMap(16);
        map.put("code", "000");
        map.put("msg", "成功");
        return map;
    }
    
    /**
     * @param id
     * @return java.util.Map
     * @Description:更新定时任务
     * @Author:SimonHu
     * @Date: 2020/5/13 21:58
     */
    public Map updateIsPause(Long id) {
        QuartzJob quartzJob = queQuartzJobById(id);
        if (quartzJob.getIsPause()) {
            quartzManage.resumeJob(quartzJob);
            quartzJob.setIsPause(false);
        } else {
            quartzManage.pauseJob(quartzJob);
            quartzJob.setIsPause(true);
        }
        quartzJobMapper.updateJobById(quartzJob);
        Map map = new HashMap(16);
        map.put("code", "000");
        map.put("msg", "成功");
        return map;
    }
    
    /**
     * @param id
     * @return java.util.Map
     * @Description:删除定时任务
     * @Author:SimonHu
     * @Date: 2020/5/14 10:56
     */
    @Transactional(rollbackFor = Exception.class)
    public Map delete(Long id) {
        QuartzJob quartzJob = queQuartzJobById(id);
        quartzJob.setStatus(0);
        //删除job
        quartzManage.deleteJob(quartzJob);
        quartzJobMapper.updateJobById(quartzJob);
        Map map = new HashMap(16);
        map.put("code", "000");
        map.put("msg", "成功");
        return map;
    }
    
    /**
     * @param quartzJob
     * @return java.util.Map
     * @Description:新增修改定时任务
     * @Author:SimonHu
     * @Date: 2020/5/14 12:18
     */
    @Transactional(rollbackFor = Exception.class)
    public Map update(QuartzJob quartzJob) {
        Map map = new HashMap(16);
        try {
            if (!CronExpression.isValidExpression(quartzJob.getCronExpression())) {
                map.put("code", "998");
                map.put("msg", "cron表达式格式错误");
                return map;
            }
            if (quartzJob.getId() == null) {
                //新增加调度任务
                quartzJobMapper.insertSelective(quartzJob);
                quartzManage.addJob(quartzJob);
            } else {
                //修改定时任务
                quartzJobMapper.updateJobById(quartzJob);
                quartzManage.updateJobCron(quartzJob);
            }
            map.put("code", "000");
            map.put("msg", "成功");
        } catch (Exception e) {
            map.put("code", "999");
            map.put("msg", e.getCause());
        }
        return map;
    }
    
    /**
     * @param id
     * @return com.simonhu.quartz.domain.QuartzJob
     * @Description:查询定时任务
     * @Author:SimonHu
     * @Date: 2020/5/14 10:55
     */
    public QuartzJob queQuartzJobById(Long id) {
        Map parmap = new HashMap(16);
        parmap.put("id", id);
        QuartzJob quartzJob = null;
        try {
            quartzJob = quartzJobMapper.queryJob(parmap).get(0);
        } catch (Exception e) {
            logger.error("----quartzJob--" + e.getMessage());
        }
        return quartzJob;
    }
}
