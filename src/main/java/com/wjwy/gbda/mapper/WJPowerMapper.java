package com.wjwy.gbda.mapper;

import com.wjwy.gbda.entity.WJPower;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 权限信息mapper
 */
@Repository
public interface WJPowerMapper {
    int deleteByPrimaryKey(@Param("powerId") Integer powerId);//根据主键删除

    int insert(WJPower record);//插入所有数据

    int insertSelective(WJPower record);//插入部分数据

    WJPower selectByPrimaryKey(@Param("powerId") Integer powerId);//根据主键查询

    int updateByPrimaryKeySelective(WJPower record);//更新部分数据

    int updateByPrimaryKey(WJPower record);//更新所有数据
}