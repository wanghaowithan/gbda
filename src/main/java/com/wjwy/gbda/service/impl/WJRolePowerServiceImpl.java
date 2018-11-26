package com.wjwy.gbda.service.impl;

import com.wjwy.gbda.entity.WJRolePower;
import com.wjwy.gbda.mapper.WJRolePowerMapper;
import com.wjwy.gbda.service.WJRolePowerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("WJRolePowerService")
public class WJRolePowerServiceImpl implements WJRolePowerService {
    @Getter
    @Setter
    @Resource
    private WJRolePowerMapper wjRolePowerMapper;

    @Override
    public int deleteByPrimaryKey(Integer wjRPId) {
        return wjRolePowerMapper.deleteByPrimaryKey(wjRPId);
    }

    @Override
    public int insert(WJRolePower record) {
        return wjRolePowerMapper.insert(record);
    }

    @Override
    public int insertSelective(WJRolePower record) {
        return wjRolePowerMapper.insertSelective(record);
    }

    @Override
    public WJRolePower selectByPrimaryKey(Integer wjRPId) {
        return wjRolePowerMapper.selectByPrimaryKey(wjRPId);
    }

    @Override
    public int updateByPrimaryKeySelective(WJRolePower record) {
        return wjRolePowerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WJRolePower record) {
        return wjRolePowerMapper.updateByPrimaryKey(record);
    }
}
