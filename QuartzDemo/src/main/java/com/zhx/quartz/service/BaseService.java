package com.zhx.quartz.service;

/**
 * Created by mulder on 15/5/14.
 */

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseService implements Serializable {

    private static final long serialVersionUID = 1293567786956029903L;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected SqlSessionFactoryBean sqlSessionFactory;

    /**
     * 查询分页数据
     *
     * @param mapperClass
     * @param sqlId
     * @param sqlParameter
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    protected List<?> getPageList(Class<?> mapperClass, String sqlId,
                                  Object sqlParameter, int pageIndex, int pageSize) throws Exception {
        SqlSession session = null;
        try {
            SqlSessionFactory sessionFactory = sqlSessionFactory.getObject();
            session = SqlSessionUtils.getSqlSession(sessionFactory);
            if (pageIndex <= 0) {
                pageIndex = 1;
            }
            if (pageSize <= 0) {
                pageSize = 10;
            }
            PageBounds pageBounds = new PageBounds(pageIndex, pageSize);
            return session.selectList(mapperClass.getName() + "." + sqlId,
                    sqlParameter, pageBounds);
        } finally {
            session.close();
        }
    }


    /**
     * 封装easyui数据分页返回结果
     * @param pageList
     * @return
     */
    protected Map packageEasyuiResultMap(PageList<Map> pageList){
        Map pagerMap = new HashMap();
        pagerMap.put("total", pageList.getPaginator().getTotalCount());
        pagerMap.put("rows",pageList);
        return pagerMap;
    }
    protected Map packageEasyuiResultMapObject(PageList<? extends Object> pageList){
        Map pagerMap = new HashMap();
        pagerMap.put("total", pageList.getPaginator().getTotalCount());
        pagerMap.put("rows",pageList);
        return pagerMap;
    }

}