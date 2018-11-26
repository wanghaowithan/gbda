package com.wjwy.gbda.service;

import com.wjwy.gbda.entity.WJRole;

/**
 * 角色信息service
 */
public interface WJRoleService {
    int deleteByPrimaryKey(Integer roleId);//根据主键删除

    int insert(WJRole record);//插入所有数据

    int insertSelective(WJRole record);//插入部分数据

    WJRole selectByPrimaryKey(Integer roleId);//根据主键查询

    int updateByPrimaryKeySelective(WJRole record);//更新部分数据

    int updateByPrimaryKey(WJRole record);//更新所有数据
}
