package com.wjwy.gbda.controller;

import com.wjwy.gbda.service.WJRSDAMLService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping(value = "/rsdaml")
@Controller
@EnableTransactionManagement
public class WJRSDAMLController {
    @Resource
    @Getter
    @Setter
    private WJRSDAMLService wjrsdamlService;
}
