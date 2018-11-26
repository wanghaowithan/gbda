package com.wjwy.gbda.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjwy.gbda.entity.WJApply;
import com.wjwy.gbda.service.WJApplyService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/apply")
@RestController
@EnableTransactionManagement
public class WJApplyController {
    @Resource
    @Setter
    @Getter
    private WJApplyService wjApplyService;

    @RequestMapping("/Apply")
    public JSONObject findOne() {
        WJApply wjApply = wjApplyService.selectByPrimaryKey(1);
        JSONObject json = new JSONObject();
        json.put("data", wjApply);
        return json;
    }
}
