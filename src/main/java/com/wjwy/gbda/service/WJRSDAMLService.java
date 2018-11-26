package com.wjwy.gbda.service;

import com.wjwy.gbda.entity.WJRSDAML;

/**
 * 目录信息service
 */
public interface WJRSDAMLService {
    int deleteByPrimaryKey(String rsdaml000);//根据主键删除

    int insert(WJRSDAML record);//插入所有数据

    int insertSelective(WJRSDAML record);//插入部分数据

    WJRSDAML selectByPrimaryKey(String rsdaml000);//根据主键查询

    int updateByPrimaryKeySelective(WJRSDAML record);//更新部分数据

    int updateByPrimaryKey(WJRSDAML record);//更新所有数据
}
