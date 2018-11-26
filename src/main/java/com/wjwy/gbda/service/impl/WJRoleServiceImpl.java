package com.wjwy.gbda.service.impl;

import com.wjwy.gbda.entity.WJRole;
import com.wjwy.gbda.mapper.WJRoleMapper;
import com.wjwy.gbda.service.WJRoleService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("WJRoleService")
public class WJRoleServiceImpl implements WJRoleService {
    @Getter
    @Setter
    @Resource
    private WJRoleMapper wjRoleMapper;

    @Override
    public int deleteByPrimaryKey(Integer roleId) {
        return wjRoleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(WJRole record) {
        return wjRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(WJRole record) {
        return wjRoleMapper.insertSelective(record);
    }

    @Override
    public WJRole selectByPrimaryKey(Integer id) {
        return wjRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WJRole record) {
        return wjRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WJRole record) {
        return wjRoleMapper.updateByPrimaryKey(record);
    }
}
