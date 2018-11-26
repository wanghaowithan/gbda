package com.wjwy.gbda.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

/**
 * 人员实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WJA01 {
    @Getter
    @Setter
    private String a0100;//人员唯一标识
    @Getter
    @Setter
    private String b0110;//单位唯一标识
    @Getter
    @Setter
    private WJB01 wjb01;//单位实体类
    @Getter
    @Setter
    private String a0101;//姓名
    @Getter
    @Setter
    private String aSpell;//姓名首字母
    @Getter
    @Setter
    private String a0104;//性别
    @Getter
    @Setter
    private String a0107;//出生日期
    @Getter
    @Setter
    private String a0117;//民族
    @Getter
    @Setter
    private String a0114;//籍贯
    @Getter
    @Setter
    private String birthplace;//出生地
    @Getter
    @Setter
    private String a0141;//参加工作日期
    @Getter
    @Setter
    private String a2205;//政治面貌
    @Getter
    @Setter
    private String a2210;//入党时间
    @Getter
    @Setter
    private Short a0151;//工龄
    @Getter
    @Setter
    private String seniorityBegin;//扣除工龄起始时间
    @Getter
    @Setter
    private String seniorityEnd;//扣除工龄截止时间
    @Getter
    @Setter
    private String seniorityContinue;//连续工龄起始时间
    @Getter
    @Setter
    private String seniorityCut;//扣除工龄年限
    @Getter
    @Setter
    private String seniorityNote;//认定工龄备注
    @Getter
    @Setter
    private String workUnits;//工作单位及职务
    @Getter
    @Setter
    private String a0184;//公民身份号码
    @Getter
    @Setter
    private String educationBackground;//最高学历
    @Getter
    @Setter
    private String educationBegin;//最高学历入学日期
    @Getter
    @Setter
    private String educationEnd;//最高学历毕业日期
    @Getter
    @Setter
    private String fullEducation;//全日制最高学历
    @Getter
    @Setter
    private String fullEducationInfo;//全日制最高学历毕业院校及专业
    @Getter
    @Setter
    private String nowEducation;//在职最高学历毕业院校及专业
    @Getter
    @Setter
    private String nowEducationInfo;//在职最高学历毕业院校及专业
    @Getter
    @Setter
    private String a0440;//学位
    @Getter
    @Setter
    private String a0455;//学位授予单位
    @Getter
    @Setter
    private String a8505;//档案号
    @Getter
    @Setter
    private Integer recordBox;//档案箱号
    @Getter
    @Setter
    private String isCopy;//正副本
    @Getter
    @Setter
    private Integer recordCount;//档案卷数
    @Getter
    @Setter
    private Integer count;//份数
    @Getter
    @Setter
    private String a8515;//转入时间
    @Getter
    @Setter
    private String a8520;//来档单位
    @Getter
    @Setter
    private String updateTime;//更新日期
    @Getter
    @Setter
    private List<WJRSDAML> wjrsdamlList;
}