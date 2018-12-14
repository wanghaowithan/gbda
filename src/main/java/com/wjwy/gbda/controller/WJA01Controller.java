package com.wjwy.gbda.controller;

import com.wjwy.gbda.entity.WJA01;
import com.wjwy.gbda.service.WJA01Service;
import com.wjwy.gbda.util.ReturnResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/a01")
@Controller
@EnableTransactionManagement
public class WJA01Controller {
    @Resource
    @Getter
    @Setter
    private WJA01Service wja01Service;
    @Getter
    @Setter
    @Resource
    private ReturnResult returnResult;

    /**
     * @param a0100 人员ID
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/deleteById/{a0100}")
    public ReturnResult deleteByPrimaryKey(@PathVariable("a0100") String a0100) {
        return returnResult.getReturnResult(wja01Service.deleteByPrimaryKey(a0100),
                returnResult, a0100, "删除");
    }

    /**
     * @param record 完整人员数据
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/insertAll")
    public ReturnResult insert(WJA01 record) {
        return returnResult.getReturnResult(wja01Service.insert(record),
                returnResult, record, "插入");
    }

    /**
     * @param record 部分人员数据
     * @return 状态码信息
     */
    @PostMapping("/insert")
    @ResponseBody
    public ReturnResult insertSelective(WJA01 record) {
        return returnResult.getReturnResult(wja01Service.insertSelective(record),
                returnResult, record, "插入");
    }

    /**
     * @param a0100 人员ID
     * @return 状态码信息
     */
    @PostMapping("/select/{a0100}")
    @ResponseBody
    public ReturnResult selectByPrimaryKey(@PathVariable("a0100") String a0100) {
        WJA01 wja01 = wja01Service.selectByPrimaryKey(a0100);
        return returnResult.getSelectReturnResult(returnResult, wja01, "查询");
    }

    /**
     * @param record 部分人员信息
     * @return 状态码信息
     */
    @PutMapping("/update")
    @ResponseBody
    public ReturnResult updateByPrimaryKeySelective(WJA01 record) {
        return returnResult.getReturnResult(wja01Service.updateByPrimaryKeySelective(record), returnResult, record, "修改");
    }

    /**
     * @param record 完整人员信息
     * @return 状态码信息
     */
    @PutMapping("/updateAll")
    @ResponseBody
    public ReturnResult updateByPrimaryKey(WJA01 record) {
        return returnResult.getReturnResult(wja01Service.updateByPrimaryKey(record),
                returnResult, record, "修改");
    }
}
