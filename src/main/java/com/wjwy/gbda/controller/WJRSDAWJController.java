package com.wjwy.gbda.controller;

import com.wjwy.gbda.entity.WJRSDAWJ;
import com.wjwy.gbda.service.WJRSDAWJService;
import com.wjwy.gbda.util.ReturnResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/rsdawj")
@Controller
@EnableTransactionManagement
public class WJRSDAWJController {
    @Resource
    @Getter
    @Setter
    private WJRSDAWJService wjrsdawjService;
    @Resource
    @Getter
    @Setter
    private ReturnResult returnResult;

    /**
     * @param rsdawjId 材料信息ID
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/deleteById/{rsdawjId}")
    public ReturnResult deleteByPrimaryKey(@PathVariable("rsdawjId") String rsdawjId) {
        return returnResult.getReturnResult(wjrsdawjService.deleteByPrimaryKey(rsdawjId),
                returnResult, rsdawjId, "删除");
    }

    /**
     * @param record 完整材料信息数据
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/insertAll")
    public ReturnResult insert(WJRSDAWJ record) {
        return returnResult.getReturnResult(wjrsdawjService.insert(record),
                returnResult, record, "插入");
    }

    /**
     * @param record 部分材料信息数据
     * @return 状态码信息
     */
    @PostMapping("/insert")
    @ResponseBody
    public ReturnResult insertSelective(WJRSDAWJ record) {
        return returnResult.getReturnResult(wjrsdawjService.insertSelective(record),
                returnResult, record, "插入");
    }

    /**
     * @param rsdawjId 材料信息ID
     * @return 状态码信息
     */
    @PostMapping("/select/{rsdawjId}")
    @ResponseBody
    public ReturnResult selectByPrimaryKey(@PathVariable("rsdawjId") String rsdawjId) {
        WJRSDAWJ wjRolePower = wjrsdawjService.selectByPrimaryKey(rsdawjId);
        return returnResult.getSelectReturnResult(returnResult, wjRolePower, "查询");
    }

    /**
     * @param record 部分材料信息数据
     * @return 状态码信息
     */
    @PutMapping("/update")
    @ResponseBody
    public ReturnResult updateByPrimaryKeySelective(WJRSDAWJ record) {
        return returnResult.getReturnResult(wjrsdawjService.updateByPrimaryKeySelective(record), returnResult, record, "修改");
    }

    /**
     * @param record 完整材料信息数据
     * @return 状态码信息
     */
    @PutMapping("/updateAll")
    @ResponseBody
    public ReturnResult updateByPrimaryKey(WJRSDAWJ record) {
        return returnResult.getReturnResult(wjrsdawjService.updateByPrimaryKey(record),
                returnResult, record, "修改");
    }
}
