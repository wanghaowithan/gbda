package com.wjwy.gbda.controller;

import com.wjwy.gbda.service.WJRSDAWJService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping(value = "/rsdawj")
@Controller
@EnableTransactionManagement
public class WJRSDAWJController {
    @Resource
    @Getter
    @Setter
    private WJRSDAWJService wjrsdawjService;
}
