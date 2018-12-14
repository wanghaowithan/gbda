package com.wjwy.gbda.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjwy.gbda.entity.WJRole;
import com.wjwy.gbda.service.WJRoleService;
import com.wjwy.gbda.util.ReturnResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/role")
@Controller
@EnableTransactionManagement
public class WJRoleController {
    @Getter
    @Setter
    @Resource
    private WJRoleService wjRoleService;
    @Getter
    @Setter
    @Resource
    private ReturnResult returnResult;

    @GetMapping(value = "/roleOne")
    @ResponseBody
    //@SystemLog(module = "用户组模块", log_num = "R0100", methods = "用户组模块-查询")
    public JSONObject findOne() {
        WJRole wjRole = wjRoleService.selectByPrimaryKey(1);
        JSONObject json = new JSONObject();
        //System.out.println(wjRole.getWjRolePowerList().get(0).getWjPower().getPowerCode());
        json.put("data", wjRole);
        return json;
    }

    /**
     * @param roleId 角色ID
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/deleteById/{roleId}")
    public ReturnResult deleteByPrimaryKey(@PathVariable("roleId") Integer roleId) {
        return returnResult.getReturnResult(wjRoleService.deleteByPrimaryKey(roleId),
                returnResult, roleId, "删除");
    }

    /**
     * @param record 完整角色数据
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/insertAll")
    public ReturnResult insert(WJRole record) {
        return returnResult.getReturnResult(wjRoleService.insert(record),
                returnResult, record, "插入");
    }

    /**
     * @param record 部分角色数据
     * @return 状态码信息
     */
    @PostMapping("/insert")
    @ResponseBody
    public ReturnResult insertSelective(WJRole record) {
        return returnResult.getReturnResult(wjRoleService.insertSelective(record),
                returnResult, record, "插入");
    }

    /**
     * @param roleId 角色ID
     * @return 状态码信息
     */
    @PostMapping("/select/{roleId}")
    @ResponseBody
    public ReturnResult selectByPrimaryKey(@PathVariable("roleId") Integer roleId) {
        WJRole wjRole = wjRoleService.selectByPrimaryKey(roleId);
        return returnResult.getSelectReturnResult(returnResult, wjRole, "查询");
    }

    /**
     * @param record 部分角色信息
     * @return 状态码信息
     */
    @PutMapping("/update")
    @ResponseBody
    public ReturnResult updateByPrimaryKeySelective(WJRole record) {
        return returnResult.getReturnResult(wjRoleService.updateByPrimaryKeySelective(record), returnResult, record, "修改");
    }

    /**
     * @param record 完整角色信息
     * @return 状态码信息
     */
    @PutMapping("/updateAll")
    @ResponseBody
    public ReturnResult updateByPrimaryKey(WJRole record) {
        return returnResult.getReturnResult(wjRoleService.updateByPrimaryKey(record),
                returnResult, record, "修改");
    }
}
