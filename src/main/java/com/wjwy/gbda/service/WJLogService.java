package com.wjwy.gbda.service;

import com.wjwy.gbda.entity.WJLog;

/**
 * 日志信息service
 */
public interface WJLogService {
    int deleteByPrimaryKey(String logId);//根据主键删除

    int insert(WJLog record);//插入所有数据

    int insertSelective(WJLog record);//插入部分数据

    WJLog selectByPrimaryKey(String logId);//根据主键查询

    int updateByPrimaryKeySelective(WJLog record);//更新部分数据

    int updateByPrimaryKey(WJLog record);//更新所有数据
}
