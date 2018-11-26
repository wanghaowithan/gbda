package com.wjwy.gbda.service;

import com.wjwy.gbda.entity.WJB01;

/**
 * 单位信息service
 */
public interface WJB01Service {
    int deleteByPrimaryKey(String b0110);//根据主键删除

    int insert(WJB01 record);//插入所有数据

    int insertSelective(WJB01 record);//插入部分数据

    WJB01 selectByPrimaryKey(String b0110);//根据主键查询

    int updateByPrimaryKeySelective(WJB01 record);//更新部分数据

    int updateByPrimaryKey(WJB01 record);//更新所有数据
}
