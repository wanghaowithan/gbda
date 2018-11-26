package com.wjwy.gbda.service;

import com.wjwy.gbda.entity.WJPower;

/**
 * 权限信息service
 */
public interface WJPowerService {
    int deleteByPrimaryKey(Integer powerId);//根据主键删除

    int insert(WJPower record);//插入所有数据

    int insertSelective(WJPower record);//插入部分数据

    WJPower selectByPrimaryKey(Integer powerId);//根据主键查询

    int updateByPrimaryKeySelective(WJPower record);//更新部分数据

    int updateByPrimaryKey(WJPower record);//更新所有数据
}
