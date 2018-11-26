package com.wjwy.gbda.entity;

import lombok.*;

import java.util.List;

/**
 * 用户组实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WJRole {
    @Getter
    @Setter
    private Integer roleId;// 用户组ID
    @Getter
    @Setter
    private String roleName;// 用户组名称
    @Getter
    @Setter
    private String updateTime;//更新日期
    @Getter
    @Setter
    private List<WJRolePower> wjRolePowerList;
    @Getter
    @Setter
    private List<WJUser> wjUserList;
}