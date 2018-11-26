package com.wjwy.gbda.util;

import java.lang.annotation.*;

/**
 * 自定义日志记录注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String module() default "";

    String methods() default "";

    String log_num() default "";
}