package com.bruce.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验注解
 * @author Bruce Wong
 * @date 2023年04月19日 11:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 有任何一个角色
     */
    String[] anyRole() default "";

    /**
     * 必须有某个角色
     */
    String mustRole() default "";


}
