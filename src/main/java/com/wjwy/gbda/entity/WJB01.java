package com.wjwy.gbda.entity;

import lombok.*;

import java.util.List;

/**
 * 单位实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WJB01 {
    @Getter
    @Setter
    private String b0110;//组织机构代码
    @Getter
    @Setter
    private String b0105;//单位名称
    @Getter
    @Setter
    private String leaderId;//单位简称
    @Getter
    @Setter
    private String bSpell;//上级单位
    @Getter
    @Setter
    private String updateTime;//更新日期
    @Getter
    @Setter
    private List<WJA01> wja01List;//单位人员表
}