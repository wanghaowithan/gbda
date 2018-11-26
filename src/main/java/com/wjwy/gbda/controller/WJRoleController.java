package com.wjwy.gbda.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjwy.gbda.entity.WJRole;
import com.wjwy.gbda.service.WJRoleService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/role")
@RestController
@EnableTransactionManagement
public class WJRoleController {
    @Getter
    @Setter
    @Resource
    private WJRoleService wjRoleService;

    @RequestMapping(value = "/roleOne")
    //@SystemLog(module = "用户组模块", log_num = "R0100", methods = "用户组模块-查询")
    public JSONObject findOne() {
        WJRole wjRole = wjRoleService.selectByPrimaryKey(1);
        JSONObject json = new JSONObject();
        //System.out.println(wjRole.getWjRolePowerList().get(0).getWjPower().getPowerCode());
        json.put("data", wjRole);
        return json;
    }
}
