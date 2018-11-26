package com.wjwy.gbda.controller;

import com.wjwy.gbda.service.WJRolePowerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/rolePower")
@RestController
@EnableTransactionManagement
public class WJRolePowerController {
    @Resource
    @Getter
    @Setter
    private WJRolePowerService wjRolePowerService;
}
