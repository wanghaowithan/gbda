package com.wjwy.gbda;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.wjwy.gbda.mapper")
@ServletComponentScan
public class GbdaApplication {
    public static void main(String[] args) {
        SpringApplication.run(GbdaApplication.class, args);
    }
}
