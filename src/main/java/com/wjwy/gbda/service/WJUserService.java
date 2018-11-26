package com.wjwy.gbda.service;

import com.wjwy.gbda.entity.WJUser;

import java.util.List;

/**
 * 用户信息service
 */
public interface WJUserService {
    int deleteByPrimaryKey(Integer userId);//根据主键删除

    int insert(WJUser record);//插入所有数据

    int insertSelective(WJUser record);//插入部分数据

    WJUser selectByPrimaryKey(Integer userId);//根据主键查询

    int updateByPrimaryKeySelective(WJUser record);//更新部分数据

    int updateByPrimaryKey(WJUser record);//更新所有数据

    WJUser selectForLogin(String userName);//根据用户名查询用户
}
