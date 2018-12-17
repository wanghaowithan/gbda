package com.wjwy.gbda.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * ajax请求返回值格式
 */
@Repository
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

    public ReturnResult getReturnResult(int i, ReturnResult returnResult,
                                        Object object, String msg) {
        if (i > 0) {
            returnResult.setCode(200);
            returnResult.setMsg(msg + "成功");
            returnResult.setResult(object);
        } else {
            returnResult.setCode(400);
            returnResult.setMsg(msg + "失败");
        }
        return returnResult;
    }

    public ReturnResult getSelectReturnResult(ReturnResult returnResult,
                                              Object object, String msg) {
        if (object != null) {
            returnResult.setCode(200);
            returnResult.setMsg(msg + "成功");
            returnResult.setResult(object);
        } else {
            returnResult.setCode(400);
            returnResult.setMsg(msg + "失败");
        }
        return returnResult;
    }
}
