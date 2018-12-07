package com.wjwy.gbda.mapper;

import com.wjwy.gbda.entity.WJRolePower;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 角色权限mapper
 */
@Repository
public interface WJRolePowerMapper {
    int deleteByPrimaryKey(@Param("wjRPId") Integer wjRPId);//根据主键删除

    int insert(WJRolePower record);//插入所有数据

    int insertSelective(WJRolePower record);//插入部分数据

    WJRolePower selectByPrimaryKey(@Param("wjRPId") Integer wjRPId);//根据主键查询

    int updateByPrimaryKeySelective(WJRolePower record);//更新部分数据

    int updateByPrimaryKey(WJRolePower record);//更新所有数据

    void selectRoleWithPower();

    void selectPowerWithRole();
}