package com.wjwy.gbda.mapper;

import com.wjwy.gbda.entity.WJRSDAWJ;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 材料信息mapper
 */
@Repository
public interface WJRSDAWJMapper {
    int deleteByPrimaryKey(@Param("rsdawjId") String rsdawjId);//根据主键删除

    int insert(WJRSDAWJ record);//插入所有数据

    int insertSelective(WJRSDAWJ record);//插入部分数据

    WJRSDAWJ selectByPrimaryKey(@Param("rsdawjId") String rsdawjId);//根据主键查询

    int updateByPrimaryKeySelective(WJRSDAWJ record);//更新部分数据

    int updateByPrimaryKey(WJRSDAWJ record);//更新所有数据
}