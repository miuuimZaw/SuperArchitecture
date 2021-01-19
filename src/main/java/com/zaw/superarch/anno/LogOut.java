package com.zaw.superarch.anno;

import java.lang.annotation.*;

/**
 * 统一日志处理注解
 *
 * @author zhangaiwen
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogOut {

    /**
     * 操作描述
     *
     * @return 方法描述
     */
    String description() default "";
}
