package com.wjwy.gbda.service.impl;

import com.wjwy.gbda.entity.WJApply;
import com.wjwy.gbda.mapper.WJApplyMapper;
import com.wjwy.gbda.service.WJApplyService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("WJApplyService")
public class WJApplyServiceImpl implements WJApplyService {
    @Getter
    @Setter
    @Resource
    private WJApplyMapper wjApplyMapper;

    @Override
    public int deleteByPrimaryKey(Integer applyId) {
        return wjApplyMapper.deleteByPrimaryKey(applyId);
    }

    @Override
    public int insert(WJApply record) {
        return wjApplyMapper.insert(record);
    }

    @Override
    public int insertSelective(WJApply record) {
        return wjApplyMapper.insertSelective(record);
    }

    @Override
    public WJApply selectByPrimaryKey(Integer id) {
        return wjApplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WJApply record) {
        return wjApplyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WJApply record) {
        return wjApplyMapper.updateByPrimaryKey(record);
    }
}
