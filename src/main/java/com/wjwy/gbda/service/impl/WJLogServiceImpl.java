package com.wjwy.gbda.service.impl;

import com.wjwy.gbda.entity.WJLog;
import com.wjwy.gbda.mapper.WJLogMapper;
import com.wjwy.gbda.service.WJLogService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("WJLogService")
public class WJLogServiceImpl implements WJLogService {
    @Getter
    @Setter
    @Resource
    private WJLogMapper wjLogMapper;

    @Override
    public int deleteByPrimaryKey(String logId) {
        return wjLogMapper.deleteByPrimaryKey(logId);
    }

    @Override
    public int insert(WJLog record) {
        return wjLogMapper.insert(record);
    }

    @Override
    public int insertSelective(WJLog record) {
        return wjLogMapper.insertSelective(record);
    }

    @Override
    public WJLog selectByPrimaryKey(String logId) {
        return wjLogMapper.selectByPrimaryKey(logId);
    }

    @Override
    public int updateByPrimaryKeySelective(WJLog record) {
        return wjLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WJLog record) {
        return wjLogMapper.updateByPrimaryKey(record);
    }
}
