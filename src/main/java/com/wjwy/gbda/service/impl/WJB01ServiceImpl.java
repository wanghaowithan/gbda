package com.wjwy.gbda.service.impl;

import com.wjwy.gbda.entity.WJB01;
import com.wjwy.gbda.mapper.WJB01Mapper;
import com.wjwy.gbda.service.WJB01Service;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("WJB01Service")
public class WJB01ServiceImpl implements WJB01Service {
    @Resource
    @Getter
    @Setter
    private WJB01Mapper wjb01Mapper;

    @Override
    public int deleteByPrimaryKey(String b0110) {
        return wjb01Mapper.deleteByPrimaryKey(b0110);
    }

    @Override
    public int insert(WJB01 record) {
        return wjb01Mapper.insert(record);
    }

    @Override
    public int insertSelective(WJB01 record) {
        return wjb01Mapper.insertSelective(record);
    }

    @Override
    public WJB01 selectByPrimaryKey(String b0100) {
        return wjb01Mapper.selectByPrimaryKey(b0100);
    }

    @Override
    public int updateByPrimaryKeySelective(WJB01 record) {
        return wjb01Mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WJB01 record) {
        return wjb01Mapper.updateByPrimaryKey(record);
    }
}
