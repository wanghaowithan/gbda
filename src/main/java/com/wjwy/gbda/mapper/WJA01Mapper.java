package com.wjwy.gbda.mapper;

import com.wjwy.gbda.entity.WJA01;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 人员信息mapper
 */
@Repository
public interface WJA01Mapper {
    int deleteByPrimaryKey(@Param("a0100") String a0100);//根据主键删除

    int insert(WJA01 record);//插入所有数据

    int insertSelective(WJA01 record);//插入部分数据

    WJA01 selectByPrimaryKey(@Param("a0100") String a0100);//根据主键查询

    int updateByPrimaryKeySelective(WJA01 record);//更新部分数据

    int updateByPrimaryKey(WJA01 record);//更新所有数据

    void selectForRSDAML();

    void selectByB01();
}