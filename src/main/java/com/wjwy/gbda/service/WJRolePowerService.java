package com.wjwy.gbda.service;

import com.wjwy.gbda.entity.WJRolePower;

/**
 * 角色权限service
 */
public interface WJRolePowerService {
    int deleteByPrimaryKey(Integer wjRPId);//根据主键删除

    int insert(WJRolePower record);//插入所有数据

    int insertSelective(WJRolePower record);//插入部分数据

    WJRolePower selectByPrimaryKey(Integer wjRPId);//根据主键查询

    int updateByPrimaryKeySelective(WJRolePower record);//更新部分数据

    int updateByPrimaryKey(WJRolePower record);//更新所有数据
}
