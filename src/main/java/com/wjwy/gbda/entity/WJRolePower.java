package com.wjwy.gbda.entity;

import lombok.*;

/**
 * 角色权限实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WJRolePower {
    @Getter
    @Setter
    private Integer wjRPId;//用户组权限表ID
    @Getter
    @Setter
    private Integer powerId;
    @Getter
    @Setter
    private WJPower wjPower;//权限表对象
    @Getter
    @Setter
    private Integer roleId;
    @Getter
    @Setter
    private WJRole wjRole;//用户组对象
    @Getter
    @Setter
    private String updateTime;//更新日期
}