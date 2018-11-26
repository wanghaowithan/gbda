package com.wjwy.gbda.entity;

import lombok.*;

import java.util.List;

/**
 * 目录实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WJRSDAML {
    @Setter
    @Getter
    private String rsdaml000;//目录唯一标识
    @Setter
    @Getter
    private String a0100;
    @Setter
    @Getter
    private WJA01 wja01;//干部人员信息表
    @Setter
    @Getter
    private String rsdaml002;//类号
    @Setter
    @Getter
    private String rsdaml003;//序号
    @Setter
    @Getter
    private String rsdaml004;//材料名称
    @Setter
    @Getter
    private Short rsdaml005;//页数
    @Setter
    @Getter
    private String rsdaml006;//材料形成时间-年
    @Setter
    @Getter
    private String rsdaml007;//材料形成时间-月
    @Setter
    @Getter
    private String rsdaml008;//材料形成时间-日
    @Setter
    @Getter
    private String rsdaml009;//备注
    @Setter
    @Getter
    private String updateTime;//更新日期
    @Getter
    @Setter
    private List<WJRSDAWJ> wjrsdawjList;//零散材料表
}