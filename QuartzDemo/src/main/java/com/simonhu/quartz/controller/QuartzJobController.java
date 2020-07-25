package com.simonhu.quartz.controller;

import com.simonhu.page.Page;
import com.simonhu.quartz.domain.QuartzJob;
import com.simonhu.quartz.domain.QuartzLog;
import com.simonhu.quartz.mapper.QuartzLogMapper;
import com.simonhu.quartz.service.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Controller
@RequestMapping("/api/jobs")
public class QuartzJobController {
    private static final String ENTITY_NAME = "quartzJob";
    @Autowired
    private QuartzJobService quartzJobService;
    @Autowired
    private QuartzLogMapper quartzLogMapper;
    
    /**
     * @param
     * @param
     * @return org.springframework.http.ResponseEntity<java.lang.Object>
     * @Description:查询定时任务
     * @Author:SimonHu
     * @Date: 2020/5/13 16:32
     */
    @RequestMapping("/queryAllPage")
    public ModelAndView queryAllPage(ModelAndView modelAndView) {
        modelAndView.setViewName("jobs/queryAllPage");
        return modelAndView;
    }
    
    /**
     * @param modelAndView
     * @param id
     * @return org.springframework.web.servlet.ModelAndView
     * @Description:修改页面
     * @Author:SimonHu
     * @Date: 2020/5/14 10:58
     */
    @RequestMapping("/jobForm")
    public ModelAndView jobForm(ModelAndView modelAndView, @RequestParam(required = false) Long id) {
        modelAndView.setViewName("jobs/jobForm");
        if (null == id) {
            modelAndView.addObject("result", null);
        } else {
            QuartzJob result = quartzJobService.queQuartzJobById(id);
            modelAndView.addObject("result", result);
        }
        return modelAndView;
    }
    /**
      * @Description:log列表
      * @Author:SimonHu
      * @Date: 2020/5/14 15:00
      * @param modelAndView
      * @return org.springframework.web.servlet.ModelAndView
      */
    @RequestMapping("/logForm")
    public ModelAndView logForm(ModelAndView modelAndView) {
        modelAndView.setViewName("jobs/logList");
        return modelAndView;
    }
    /**
      * @Description:错误信息
      * @Author:SimonHu
      * @Date: 2020/5/14 15:53
      * @param modelAndView
      * @param id
      * @return org.springframework.web.servlet.ModelAndView
      */
    @RequestMapping("/exceptionInfo")
    public ModelAndView exceptionInfo(ModelAndView modelAndView,String id) {
        Map map = new HashMap(16);
        map.put("id",id);
        QuartzLog log = quartzLogMapper.selectByPrimaryKey(map).get(0);
        modelAndView.addObject("data",log.getExceptionDetail());
        modelAndView.setViewName("jobs/exceptionInfo");
        return modelAndView;
    }
    
    /**
     * 查询数据
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryAllData", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map queryAllData(Page page) {
        Map listInfo = null;
        try {
            listInfo = quartzJobService.queryAll(page.getPage(), page.getRows());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }
    /**
      * @Description:查询日志
      * @Author:SimonHu
      * @Date: 2020/5/14 14:55
      * @param page
      * @return java.util.Map
      */
    @RequestMapping(value = "/queryAllLog", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map queryAllLog(Page page) {
        Map listInfo = null;
        try {
            listInfo = quartzJobService.queryAllLog(page.getPage(), page.getRows());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listInfo;
    }
    
    /**
     * @param id
     * @return org.springframework.http.ResponseEntity<java.lang.Object>
     * @Description:执行定时任务
     * @Author:SimonHu
     * @Date: 2020/5/13 17:44
     */
    @RequestMapping(value = "/execution/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map execution(@PathVariable Long id) {
        Map map = quartzJobService.execution(id);
        return map;
    }
    
    /**
     * @param id
     * @return java.util.Map
     * @Description:更改定时任务状态
     * @Author:SimonHu
     * @Date: 2020/5/14 10:25
     */
    @RequestMapping(value = "/updateIsPause/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map updateIsPause(@PathVariable Long id) {
        Map map = quartzJobService.updateIsPause(id);
        return map;
    }
    
    /**
     * @param id
     * @return java.util.Map
     * @Description:删除定时任务
     * @Author:SimonHu
     * @Date: 2020/5/14 10:25
     */
    @RequestMapping(value = "/del/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map del(@PathVariable Long id) {
        Map map = quartzJobService.delete(id);
        return map;
    }
    /**
      * @Description:更新调度任务
      * @Author:SimonHu
      * @Date: 2020/5/14 12:10
      * @param quartzJob
      * @return java.util.Map
      */
    @RequestMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map update(QuartzJob quartzJob) {
        Map map = quartzJobService.update(quartzJob);
        return map;
    }
}
