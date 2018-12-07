package com.wjwy.gbda.mapper;

import com.wjwy.gbda.entity.WJRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 角色信息mapper
 */
@Repository
public interface WJRoleMapper {
    int deleteByPrimaryKey(@Param("roleId") Integer roleId);//根据主键删除

    int insert(WJRole record);//插入所有数据

    int insertSelective(WJRole record);//插入部分数据

    WJRole selectByPrimaryKey(@Param("roleId") Integer roleId);//根据主键查询

    int updateByPrimaryKeySelective(WJRole record);//更新部分数据

    int updateByPrimaryKey(WJRole record);//更新所有数据

    void selectForUser();
}