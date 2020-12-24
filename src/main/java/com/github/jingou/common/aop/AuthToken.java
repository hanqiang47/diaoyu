package com.github.jingou.common.aop;


import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthToken {
    /**
     * 访问所需的身份，默认为0，为登录即可访问，可以多个定义
     */
    String[] role_name() default "visitor";
}
