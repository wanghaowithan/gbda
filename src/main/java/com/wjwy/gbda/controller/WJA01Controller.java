package com.wjwy.gbda.controller;

import com.wjwy.gbda.service.WJA01Service;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/a01")
@RestController
@EnableTransactionManagement
public class WJA01Controller {
    @Resource
    @Getter
    @Setter
    private WJA01Service wja01Service;
}
