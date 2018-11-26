package com.wjwy.gbda.entity;

import lombok.*;

/**
 * 阅档申请实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WJApply {
    @Getter
    @Setter
    private Integer applyId;//申请ID
    @Getter
    @Setter
    private Integer userId;//用户表对象
    @Getter
    @Setter
    private WJUser wjUser;//用户表对象
    @Getter
    @Setter
    private String name1;//申请人1姓名
    @Getter
    @Setter
    private String duty1;//申请人1职务
    @Getter
    @Setter
    private String political1;//申请人1政治面貌
    @Getter
    @Setter
    private String name2;//申请人2姓名
    @Getter
    @Setter
    private String duty2;//申请人2职务
    @Getter
    @Setter
    private String political2;//申请人2政治面貌
    @Getter
    @Setter
    private String applyUnit;//查阅单位
    @Getter
    @Setter
    private String applyLeader;//单位负责人
    @Getter
    @Setter
    private String applyReason;//查阅理由
    @Getter
    @Setter
    private String info;//备注
    @Getter
    @Setter
    private String readTime;//查阅时间
    @Getter
    @Setter
    private String startTime;//查阅开始时间
    @Getter
    @Setter
    private String endTime;//查阅结束时间
    @Getter
    @Setter
    private String a0100s;//要查阅档案的人员
    @Getter
    @Setter
    private String status;//审批状态
    @Getter
    @Setter
    private String updateTime;//更新时间
    @Getter
    @Setter
    private String readInfo;//允许查阅内容
}