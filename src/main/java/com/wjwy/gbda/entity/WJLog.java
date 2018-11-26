package com.wjwy.gbda.entity;

import lombok.*;

/**
 * 日志实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WJLog {
    @Getter
    @Setter
    private String logId;//日志ID
    @Getter
    @Setter
    private String logUserName;//操作用户名
    @Getter
    @Setter
    private String module;//操作模块
    @Getter
    @Setter
    private String description;//操作描述
    @Getter
    @Setter
    private String ip;//IP地址
    @Getter
    @Setter
    private String time;//操作时间
    @Getter
    @Setter
    private String logNum;//操作ID
    @Getter
    @Setter
    private String commite;//是否成功提交
    @Getter
    @Setter
    private String updateTime;//更新日期
}