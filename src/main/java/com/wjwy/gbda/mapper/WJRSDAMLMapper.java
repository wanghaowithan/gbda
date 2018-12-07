package com.wjwy.gbda.mapper;

import com.wjwy.gbda.entity.WJRSDAML;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 目录信息mapper
 */
@Repository
public interface WJRSDAMLMapper {
    int deleteByPrimaryKey(@Param("rsdaml000") String rsdaml000);//根据主键删除

    int insert(WJRSDAML record);//插入所有数据

    int insertSelective(WJRSDAML record);//插入部分数据

    WJRSDAML selectByPrimaryKey(@Param("rsdaml000") String rsdaml000);//根据主键查询

    int updateByPrimaryKeySelective(WJRSDAML record);//更新部分数据

    int updateByPrimaryKey(WJRSDAML record);//更新所有数据

    void selectByA01();

    void selectForRSDAWJ();
}