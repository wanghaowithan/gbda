package com.wjwy.gbda.entity;

import lombok.*;

/**
 * 材料实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WJRSDAWJ {
    @Getter
    @Setter
    private String rsdawjId;//文件ID
    @Getter
    @Setter
    private String rsdaml000;
    @Getter
    @Setter
    private WJRSDAML wjrsdaml;//目录表
    @Getter
    @Setter
    private String rsdawj001;//页序号
    @Getter
    @Setter
    private String rsdawj003;//原始文件名
    @Getter
    @Setter
    private String rsdawj004;//优化文件名
    @Getter
    @Setter
    private String updateTime;//更新日期
}