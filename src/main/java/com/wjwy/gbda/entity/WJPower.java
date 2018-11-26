package com.wjwy.gbda.entity;

import lombok.*;

import java.util.List;

/**
 * 权限实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WJPower {
    @Getter
    @Setter
    private Integer powerId;//权限ID
    @Getter
    @Setter
    private String powerCode;//权限模块
    @Getter
    @Setter
    private String powerInfo;//权限描述
    @Getter
    @Setter
    private String powerUrl;//权限对应资源地址
    @Getter
    @Setter
    private Integer pid;//父类权限ID
    @Getter
    @Setter
    private String updateTime;//更新日期
    @Getter
    @Setter
    private List<WJRolePower> wjRolePowerList;
}