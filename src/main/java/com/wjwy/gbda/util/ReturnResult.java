package com.wjwy.gbda.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * ajax请求返回值格式
 */
public class ReturnResult implements Serializable {
    @Getter
    @Setter
    private int code = 0;
    @Getter
    @Setter
    private String msg = null;
    @Getter
    @Setter
    private Object token;
    @Getter
    @Setter
    private Object result;
}
