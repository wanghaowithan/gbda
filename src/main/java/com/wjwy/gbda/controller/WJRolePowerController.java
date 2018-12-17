package com.wjwy.gbda.controller;

import com.wjwy.gbda.entity.WJRolePower;
import com.wjwy.gbda.service.WJRolePowerService;
import com.wjwy.gbda.util.ReturnResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/rolePower")
@Controller
@EnableTransactionManagement
public class WJRolePowerController {
    @Resource
    @Getter
    @Setter
    private WJRolePowerService wjRolePowerService;
    @Resource
    @Getter
    @Setter
    private ReturnResult returnResult;

    /**
     * @param wjRPId 角色权限ID
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/deleteById/{wjRPId}")
    public ReturnResult deleteByPrimaryKey(@PathVariable("wjRPId") Integer wjRPId) {
        return returnResult.getReturnResult(wjRolePowerService.deleteByPrimaryKey(wjRPId),
                returnResult, wjRPId, "删除");
    }

    /**
     * @param record 完整角色权限数据
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/insertAll")
    public ReturnResult insert(WJRolePower record) {
        return returnResult.getReturnResult(wjRolePowerService.insert(record),
                returnResult, record, "插入");
    }

    /**
     * @param record 部分角色权限数据
     * @return 状态码信息
     */
    @PostMapping("/insert")
    @ResponseBody
    public ReturnResult insertSelective(WJRolePower record) {
        return returnResult.getReturnResult(wjRolePowerService.insertSelective(record),
                returnResult, record, "插入");
    }

    /**
     * @param wjRPId 角色权限ID
     * @return 状态码信息
     */
    @PostMapping("/select/{wjRPId}")
    @ResponseBody
    public ReturnResult selectByPrimaryKey(@PathVariable("wjRPId") Integer wjRPId) {
        WJRolePower wjRolePower = wjRolePowerService.selectByPrimaryKey(wjRPId);
        return returnResult.getSelectReturnResult(returnResult, wjRolePower, "查询");
    }

    /**
     * @param record 部分角色权限信息
     * @return 状态码信息
     */
    @PutMapping("/update")
    @ResponseBody
    public ReturnResult updateByPrimaryKeySelective(WJRolePower record) {
        return returnResult.getReturnResult(wjRolePowerService.updateByPrimaryKeySelective(record), returnResult, record, "修改");
    }

    /**
     * @param record 完整角色权限信息
     * @return 状态码信息
     */
    @PutMapping("/updateAll")
    @ResponseBody
    public ReturnResult updateByPrimaryKey(WJRolePower record) {
        return returnResult.getReturnResult(wjRolePowerService.updateByPrimaryKey(record),
                returnResult, record, "修改");
    }
}
