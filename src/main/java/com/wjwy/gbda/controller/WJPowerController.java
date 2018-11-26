package com.wjwy.gbda.controller;

import com.wjwy.gbda.service.WJPowerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/power")
@RestController
@EnableTransactionManagement
public class WJPowerController {
    @Resource
    @Getter
    @Setter
    private WJPowerService wjPowerService;
}
