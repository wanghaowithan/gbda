package com.wjwy.gbda.service.impl;

import com.wjwy.gbda.entity.WJRSDAWJ;
import com.wjwy.gbda.mapper.WJRSDAWJMapper;
import com.wjwy.gbda.service.WJRSDAWJService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("WJRSDAWJService")
public class WJRSDAWJServiceImpl implements WJRSDAWJService {
    @Getter
    @Setter
    @Resource
    private WJRSDAWJMapper wjrsdawjMapper;

    @Override
    public int deleteByPrimaryKey(String rsdawjId) {
        return wjrsdawjMapper.deleteByPrimaryKey(rsdawjId);
    }

    @Override
    public int insert(WJRSDAWJ record) {
        return wjrsdawjMapper.insert(record);
    }

    @Override
    public int insertSelective(WJRSDAWJ record) {
        return wjrsdawjMapper.insertSelective(record);
    }

    @Override
    public WJRSDAWJ selectByPrimaryKey(String rsdawjId) {
        return wjrsdawjMapper.selectByPrimaryKey(rsdawjId);
    }

    @Override
    public int updateByPrimaryKeySelective(WJRSDAWJ record) {
        return wjrsdawjMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WJRSDAWJ record) {
        return wjrsdawjMapper.updateByPrimaryKey(record);
    }
}
