package com.wjwy.gbda.mapper;

import com.wjwy.gbda.entity.WJApply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 阅档申请mapper
 */
@Repository
public interface WJApplyMapper {
    int deleteByPrimaryKey(@Param("applyId") Integer applyId);//根据主键删除

    int insert(WJApply record);//插入所有数据

    int insertSelective(WJApply record);//插入部分数据

    WJApply selectByPrimaryKey(@Param("applyId") Integer applyId);//根据主键查询

    int updateByPrimaryKeySelective(WJApply record);//更新部分数据

    int updateByPrimaryKey(WJApply record);//更新所有数据

    void selectByUserId();
}