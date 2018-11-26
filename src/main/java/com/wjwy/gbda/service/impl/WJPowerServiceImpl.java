package com.wjwy.gbda.service.impl;

import com.wjwy.gbda.entity.WJPower;
import com.wjwy.gbda.mapper.WJPowerMapper;
import com.wjwy.gbda.service.WJPowerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("WJPowerService")
public class WJPowerServiceImpl implements WJPowerService {
    @Resource
    @Getter
    @Setter
    private WJPowerMapper wjPowerMapper;

    @Override
    public int deleteByPrimaryKey(Integer powerId) {
        return wjPowerMapper.deleteByPrimaryKey(powerId);
    }

    @Override
    public int insert(WJPower record) {
        return wjPowerMapper.insert(record);
    }

    @Override
    public int insertSelective(WJPower record) {
        return wjPowerMapper.insertSelective(record);
    }

    @Override
    public WJPower selectByPrimaryKey(Integer powerId) {
        return wjPowerMapper.selectByPrimaryKey(powerId);
    }

    @Override
    public int updateByPrimaryKeySelective(WJPower record) {
        return wjPowerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WJPower record) {
        return wjPowerMapper.updateByPrimaryKey(record);
    }
}
