/**
 * $Id:$
 * Copyright 2014-2015 Beijing Runlin Automobile Technology Company Ltd. All rights reserved.
 */
package com.biminds.framework.mvc.mybatis;

import com.biminds.framework.mvc.entity.PageForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * MyBatis通用Dao，无泛型
 *
 * @author Jades.He
 * @date 2015年10月15日 下午1:28:26
 */
public class BaseCommonDao extends SqlSessionDaoSupport {

    /**
     * 默认注入主业务库的SqlSessionFactory
     */
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 根据不同的数据库方言，生成类似于T-SQL中的<code>SELECT TOP N</code>查询语句
     *
     * @param top       查询限定数量
     * @param statement statement key
     * @return 结果集合
     * @author 何珏 2014-8-30
     */
    protected <T> List<T> findTop(int top, String statement) {
        return findTop(top, statement, null);
    }

    /**
     * 根据不同的数据库方言，生成类似于T-SQL中的<code>SELECT TOP N</code>查询语句
     *
     * @param top       查询限定数量
     * @param statement statement key
     * @param parameter 查询参数
     * @return 结果集合
     * @author 何珏 2014-8-30
     */
    protected <T> List<T> findTop(int top, String statement, Object parameter) {
        List<T> list = getSqlSession().selectList(statement, parameter, new RowBounds(0, top));
        return list;
    }

    /**
     * 根据不同的数据库方言，生成类似于T-SQL中的<code>SELECT TOP 1</code>查询语句
     *
     * @param statement statement key
     * @return 结果实体
     * @author 何珏 2014-11-4
     */
    public <T> T findTopOne(String statement) {
        return findTopOne(statement, null);
    }

    /**
     * 根据不同的数据库方言，生成类似于T-SQL中的<code>SELECT TOP 1</code>查询语句
     *
     * @param statement statement key
     * @param parameter 查询参数
     * @return 结果实体
     * @author 何珏 2014-11-4
     */
    public <T> T findTopOne(String statement, Object parameter) {
        List<T> list = findTop(1, statement, parameter);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 通用分页查询Model
     *
     * @param statementKey 查询方法
     * @param pageForm     分页信息
     * @param parameter    查询参数
     * @return 分页结果
     * @author Jades.He
     * @date 2015年6月27日 下午5:52:36
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public <M> PageInfo<M> pageFindModel(String statementKey, PageForm pageForm, Object parameter) {
        PageHelper.startPage(pageForm.getPage(), pageForm.getRows());
        List<M> list = getSqlSession().selectList(statementKey, parameter);
        PageInfo<M> pageInfo = new PageInfo(list);

        return pageInfo;
    }
}
