package com.wjwy.gbda.service.impl;

import com.wjwy.gbda.entity.WJRSDAML;
import com.wjwy.gbda.mapper.WJRSDAMLMapper;
import com.wjwy.gbda.service.WJRSDAMLService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("WJRSDAMLService")
public class WJRSDAMLServiceImpl implements WJRSDAMLService {
    @Getter
    @Setter
    @Resource
    private WJRSDAMLMapper wjrsdamlMapper;

    @Override
    public int deleteByPrimaryKey(String rsdaml000) {
        return wjrsdamlMapper.deleteByPrimaryKey(rsdaml000);
    }

    @Override
    public int insert(WJRSDAML record) {
        return wjrsdamlMapper.insert(record);
    }

    @Override
    public int insertSelective(WJRSDAML record) {
        return wjrsdamlMapper.insertSelective(record);
    }

    @Override
    public WJRSDAML selectByPrimaryKey(String rsdaml000) {
        return wjrsdamlMapper.selectByPrimaryKey(rsdaml000);
    }

    @Override
    public int updateByPrimaryKeySelective(WJRSDAML record) {
        return wjrsdamlMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WJRSDAML record) {
        return wjrsdamlMapper.updateByPrimaryKey(record);
    }
}
