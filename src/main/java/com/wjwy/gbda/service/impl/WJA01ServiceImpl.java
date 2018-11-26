package com.wjwy.gbda.service.impl;

import com.wjwy.gbda.entity.WJA01;
import com.wjwy.gbda.mapper.WJA01Mapper;
import com.wjwy.gbda.service.WJA01Service;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("WJA01Service")
public class WJA01ServiceImpl implements WJA01Service {
    @Resource
    @Getter
    @Setter
    private WJA01Mapper wja01Mapper;

    @Override
    public int deleteByPrimaryKey(String a0100) {
        return wja01Mapper.deleteByPrimaryKey(a0100);
    }

    @Override
    public int insert(WJA01 record) {
        return wja01Mapper.insert(record);
    }

    @Override
    public int insertSelective(WJA01 record) {
        return wja01Mapper.insertSelective(record);
    }

    @Override
    public WJA01 selectByPrimaryKey(String a0100) {
        return wja01Mapper.selectByPrimaryKey(a0100);
    }

    @Override
    public int updateByPrimaryKeySelective(WJA01 record) {
        return wja01Mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WJA01 record) {
        return wja01Mapper.updateByPrimaryKey(record);
    }
}
