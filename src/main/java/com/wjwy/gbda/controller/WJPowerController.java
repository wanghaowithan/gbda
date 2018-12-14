package com.wjwy.gbda.controller;

import com.wjwy.gbda.entity.WJPower;
import com.wjwy.gbda.service.WJPowerService;
import com.wjwy.gbda.util.ReturnResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/power")
@Controller
@EnableTransactionManagement
public class WJPowerController {
    @Resource
    @Getter
    @Setter
    private WJPowerService wjPowerService;
    @Getter
    @Setter
    @Resource
    private ReturnResult returnResult;

    /**
     * @param powerId 权限ID
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/deleteById/{powerId}")
    public ReturnResult deleteByPrimaryKey(@PathVariable("powerId") Integer powerId) {
        return returnResult.getReturnResult(wjPowerService.deleteByPrimaryKey(powerId),
                returnResult, powerId, "删除");
    }

    /**
     * @param record 完整权限数据
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/insertAll")
    public ReturnResult insert(WJPower record) {
        return returnResult.getReturnResult(wjPowerService.insert(record),
                returnResult, record, "插入");
    }

    /**
     * @param record 部分权限数据
     * @return 状态码信息
     */
    @PostMapping("/insert")
    @ResponseBody
    public ReturnResult insertSelective(WJPower record) {
        return returnResult.getReturnResult(wjPowerService.insertSelective(record),
                returnResult, record, "插入");
    }

    /**
     * @param powerId 权限ID
     * @return 状态码信息
     */
    @PostMapping("/select/{powerId}")
    @ResponseBody
    public ReturnResult selectByPrimaryKey(@PathVariable("powerId") Integer powerId) {
        WJPower wjPower = wjPowerService.selectByPrimaryKey(powerId);
        return returnResult.getSelectReturnResult(returnResult, wjPower, "查询");
    }

    /**
     * @param record 部分权限信息
     * @return 状态码信息
     */
    @PutMapping("/update")
    @ResponseBody
    public ReturnResult updateByPrimaryKeySelective(WJPower record) {
        return returnResult.getReturnResult(wjPowerService.updateByPrimaryKeySelective(record), returnResult, record, "修改");
    }

    /**
     * @param record 完整权限信息
     * @return 状态码信息
     */
    @PutMapping("/updateAll")
    @ResponseBody
    public ReturnResult updateByPrimaryKey(WJPower record) {
        return returnResult.getReturnResult(wjPowerService.updateByPrimaryKey(record),
                returnResult, record, "修改");
    }
}
