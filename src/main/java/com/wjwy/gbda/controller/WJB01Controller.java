package com.wjwy.gbda.controller;

import com.wjwy.gbda.entity.WJB01;
import com.wjwy.gbda.service.WJB01Service;
import com.wjwy.gbda.util.ReturnResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/b01")
@Controller
@EnableTransactionManagement
public class WJB01Controller {
    @Resource
    @Getter
    @Setter
    private WJB01Service wjb01Service;
    @Resource
    @Getter
    @Setter
    private ReturnResult returnResult;

    /**
     * @param b0110 单位ID
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/deleteById/{b0110}")
    public ReturnResult deleteByPrimaryKey(@PathVariable("b0110") String b0110) {
        return returnResult.getReturnResult(wjb01Service.deleteByPrimaryKey(b0110),
                returnResult, b0110, "删除");
    }

    /**
     * @param record 完整单位数据
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/insertAll")
    public ReturnResult insert(WJB01 record) {
        return returnResult.getReturnResult(wjb01Service.insert(record),
                returnResult, record, "插入");
    }

    /**
     * @param record 部分单位数据
     * @return 状态码信息
     */
    @PostMapping("/insert")
    @ResponseBody
    public ReturnResult insertSelective(WJB01 record) {
        return returnResult.getReturnResult(wjb01Service.insertSelective(record),
                returnResult, record, "插入");
    }

    /**
     * @param b0110 单位ID
     * @return 状态码信息
     */
    @PostMapping("/select/{b0110}")
    @ResponseBody
    public ReturnResult selectByPrimaryKey(@PathVariable("b0110") String b0110) {
        WJB01 wjb01 = wjb01Service.selectByPrimaryKey(b0110);
        return returnResult.getSelectReturnResult(returnResult, wjb01, "查询");
    }

    /**
     * @param record 部分单位信息
     * @return 状态码信息
     */
    @PutMapping("/update")
    @ResponseBody
    public ReturnResult updateByPrimaryKeySelective(WJB01 record) {
        return returnResult.getReturnResult(wjb01Service.updateByPrimaryKeySelective(record), returnResult, record, "修改");
    }

    /**
     * @param record 完整单位信息
     * @return 状态码信息
     */
    @PutMapping("/updateAll")
    @ResponseBody
    public ReturnResult updateByPrimaryKey(WJB01 record) {
        return returnResult.getReturnResult(wjb01Service.updateByPrimaryKey(record),
                returnResult, record, "修改");
    }
}
