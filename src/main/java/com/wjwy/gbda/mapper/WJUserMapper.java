package com.wjwy.gbda.mapper;

import com.wjwy.gbda.entity.WJUser;
import org.springframework.stereotype.Repository;

/**
 * 用户信息mapper
 */
@Repository
public interface WJUserMapper {
    int deleteByPrimaryKey(Integer userId);//根据主键删除

    int insert(WJUser record);//插入所有数据

    int insertSelective(WJUser record);//插入部分数据

    WJUser selectByPrimaryKey(Integer userId);//根据主键查询

    int updateByPrimaryKeySelective(WJUser record);//更新部分数据

    int updateByPrimaryKey(WJUser record);//更新所有数据

    WJUser selectForLogin(String userName);//根据用户名查询用户
}