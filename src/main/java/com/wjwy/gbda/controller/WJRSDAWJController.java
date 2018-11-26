package com.wjwy.gbda.controller;

import com.wjwy.gbda.service.WJRSDAWJService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/rsdawj")
@RestController
@EnableTransactionManagement
public class WJRSDAWJController {
    @Resource
    @Getter
    @Setter
    private WJRSDAWJService wjrsdawjService;
}
