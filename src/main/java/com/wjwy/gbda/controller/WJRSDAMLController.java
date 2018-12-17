package com.wjwy.gbda.controller;

import com.wjwy.gbda.entity.WJRSDAML;
import com.wjwy.gbda.service.WJRSDAMLService;
import com.wjwy.gbda.util.ReturnResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/rsdaml")
@Controller
@EnableTransactionManagement
public class WJRSDAMLController {
    @Resource
    @Getter
    @Setter
    private WJRSDAMLService wjrsdamlService;
    @Resource
    @Getter
    @Setter
    private ReturnResult returnResult;

    /**
     * @param rsdaml000 目录ID
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/deleteById/{rsdaml000}")
    public ReturnResult deleteByPrimaryKey(@PathVariable("rsdaml000") String rsdaml000) {
        return returnResult.getReturnResult(wjrsdamlService.deleteByPrimaryKey(rsdaml000),
                returnResult, rsdaml000, "删除");
    }

    /**
     * @param record 完整目录数据
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/insertAll")
    public ReturnResult insert(WJRSDAML record) {
        return returnResult.getReturnResult(wjrsdamlService.insert(record),
                returnResult, record, "插入");
    }

    /**
     * @param record 部分目录数据
     * @return 状态码信息
     */
    @PostMapping("/insert")
    @ResponseBody
    public ReturnResult insertSelective(WJRSDAML record) {
        return returnResult.getReturnResult(wjrsdamlService.insertSelective(record),
                returnResult, record, "插入");
    }

    /**
     * @param rsdaml000 目录ID
     * @return 状态码信息
     */
    @PostMapping("/select/{rsdaml000}")
    @ResponseBody
    public ReturnResult selectByPrimaryKey(@PathVariable("rsdaml000") String rsdaml000) {
        WJRSDAML wjrsdaml = wjrsdamlService.selectByPrimaryKey(rsdaml000);
        return returnResult.getSelectReturnResult(returnResult, wjrsdaml, "查询");
    }

    /**
     * @param record 部分目录信息
     * @return 状态码信息
     */
    @PutMapping("/update")
    @ResponseBody
    public ReturnResult updateByPrimaryKeySelective(WJRSDAML record) {
        return returnResult.getReturnResult(wjrsdamlService.updateByPrimaryKeySelective(record), returnResult, record, "修改");
    }

    /**
     * @param record 完整目录信息
     * @return 状态码信息
     */
    @PutMapping("/updateAll")
    @ResponseBody
    public ReturnResult updateByPrimaryKey(WJRSDAML record) {
        return returnResult.getReturnResult(wjrsdamlService.updateByPrimaryKey(record),
                returnResult, record, "修改");
    }
}
