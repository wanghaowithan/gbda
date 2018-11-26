package com.wjwy.gbda.service.impl;

import com.wjwy.gbda.entity.WJUser;
import com.wjwy.gbda.mapper.WJUserMapper;
import com.wjwy.gbda.service.WJUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("WJUserService")
public class WJUserServiceImpl implements WJUserService {
    @Resource
    @Getter
    @Setter
    private WJUserMapper wjUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return wjUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(WJUser record) {
        return wjUserMapper.insert(record);
    }

    @Override
    public int insertSelective(WJUser record) {
        return wjUserMapper.insertSelective(record);
    }

    @Override
    public WJUser selectByPrimaryKey(Integer userId) {
        return wjUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(WJUser record) {
        return wjUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WJUser record) {
        return wjUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public WJUser selectForLogin(String userName) {
        return wjUserMapper.selectForLogin(userName);
    }
}
