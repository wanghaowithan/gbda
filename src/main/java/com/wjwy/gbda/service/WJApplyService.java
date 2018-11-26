package com.wjwy.gbda.service;

import com.wjwy.gbda.entity.WJApply;

/**
 * 阅档申请service
 */
public interface WJApplyService {
    int deleteByPrimaryKey(Integer applyId);//根据主键删除

    int insert(WJApply record);//插入所有数据

    int insertSelective(WJApply record);//插入部分数据

    WJApply selectByPrimaryKey(Integer applyId);//根据主键查询

    int updateByPrimaryKeySelective(WJApply record);//更新部分数据

    int updateByPrimaryKey(WJApply record);//更新所有数据
}
