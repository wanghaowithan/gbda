package com.wjwy.gbda.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjwy.gbda.entity.WJApply;
import com.wjwy.gbda.service.WJApplyService;
import com.wjwy.gbda.util.ReturnResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/apply")
@Controller
@EnableTransactionManagement
public class WJApplyController {
    @Resource
    @Setter
    @Getter
    private WJApplyService wjApplyService;
    @Resource
    @Getter
    @Setter
    private ReturnResult returnResult;

    @RequestMapping("/Apply")
    public JSONObject findOne() {
        WJApply wjApply = wjApplyService.selectByPrimaryKey(1);
        JSONObject json = new JSONObject();
        json.put("data", wjApply);
        return json;
    }

    /**
     * @param applyId 阅档申请ID
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/deleteById/{applyId}")
    public ReturnResult deleteByPrimaryKey(@PathVariable("applyId") Integer applyId) {
        return returnResult.getReturnResult(wjApplyService.deleteByPrimaryKey(applyId),
                returnResult, applyId, "删除");
    }

    /**
     * @param record 完整阅档申请数据
     * @return 状态码信息
     */
    @ResponseBody
    @DeleteMapping("/insertAll")
    public ReturnResult insert(WJApply record) {
        return returnResult.getReturnResult(wjApplyService.insert(record),
                returnResult, record, "插入");
    }

    /**
     * @param record 部分阅档申请数据
     * @return 状态码信息
     */
    @PostMapping("/insert")
    @ResponseBody
    public ReturnResult insertSelective(WJApply record) {
        return returnResult.getReturnResult(wjApplyService.insertSelective(record),
                returnResult, record, "插入");
    }

    /**
     * @param applyId 阅档申请ID
     * @return 状态码信息
     */
    @PostMapping("/select/{applyId}")
    @ResponseBody
    public ReturnResult selectByPrimaryKey(@PathVariable("applyId") Integer applyId) {
        WJApply wjApply = wjApplyService.selectByPrimaryKey(applyId);
        return returnResult.getSelectReturnResult(returnResult, wjApply, "查询");
    }

    /**
     * @param record 部分阅档申请信息
     * @return 状态码信息
     */
    @PutMapping("/update")
    @ResponseBody
    public ReturnResult updateByPrimaryKeySelective(WJApply record) {
        return returnResult.getReturnResult(wjApplyService.updateByPrimaryKeySelective(record), returnResult, record, "修改");
    }

    /**
     * @param record 完整阅档申请信息
     * @return 状态码信息
     */
    @PutMapping("/updateAll")
    @ResponseBody
    public ReturnResult updateByPrimaryKey(WJApply record) {
        return returnResult.getReturnResult(wjApplyService.updateByPrimaryKey(record),
                returnResult, record, "修改");
    }
}
