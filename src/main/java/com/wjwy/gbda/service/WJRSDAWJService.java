package com.wjwy.gbda.service;

import com.wjwy.gbda.entity.WJRSDAWJ;

/**
 * 材料信息service
 */
public interface WJRSDAWJService {
    int deleteByPrimaryKey(String rsdawjId);//根据主键删除

    int insert(WJRSDAWJ record);//插入所有数据

    int insertSelective(WJRSDAWJ record);//插入部分数据

    WJRSDAWJ selectByPrimaryKey(String rsdawjId);//根据主键查询

    int updateByPrimaryKeySelective(WJRSDAWJ record);//更新部分数据

    int updateByPrimaryKey(WJRSDAWJ record);//更新所有数据
}
