package com.wjwy.gbda.mapper;

import com.wjwy.gbda.entity.WJB01;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 单位信息mapper
 */
@Repository
public interface WJB01Mapper {
    int deleteByPrimaryKey(@Param("b0110") String b0110);//根据主键删除

    int insert(WJB01 record);//插入所有数据

    int insertSelective(WJB01 record);//插入部分数据

    WJB01 selectByPrimaryKey(@Param("b0110") String b0110);//根据主键查询

    int updateByPrimaryKeySelective(WJB01 record);//更新部分数据

    int updateByPrimaryKey(WJB01 record);//更新所有数据

    void selectForA01();
}