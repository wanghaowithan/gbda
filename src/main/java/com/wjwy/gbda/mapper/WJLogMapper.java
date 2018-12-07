package com.wjwy.gbda.mapper;

import com.wjwy.gbda.entity.WJLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 日志信息mapper
 */
@Repository
public interface WJLogMapper {
    int deleteByPrimaryKey(@Param("logId") String logId);//根据主键删除

    int insert(WJLog record);//插入所有数据

    int insertSelective(WJLog record);//插入部分数据

    WJLog selectByPrimaryKey(@Param("logId") String logId);//根据主键查询

    int updateByPrimaryKeySelective(WJLog record);//更新部分数据

    int updateByPrimaryKey(WJLog record);//更新所有数据
}