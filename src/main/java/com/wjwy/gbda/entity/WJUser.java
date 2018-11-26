package com.wjwy.gbda.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 用户实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WJUser implements Serializable {
    @Getter
    @Setter
    private Integer userId;// 用户ID
    @Getter
    @Setter
    private Integer roleId;
    @Getter
    @Setter
    private WJRole wjRole;//用户组对象
    @Getter
    @Setter
    private String userName;// 用户名
    @Getter
    @Setter
    private String password;// 用户密码
    @Getter
    @Setter
    private Integer status;//是否有效
    @Getter
    @Setter
    private String updateTime;//更新日期
    @Getter
    @Setter
    private List<WJApply> wjApplyList;//用户阅档申请表
}