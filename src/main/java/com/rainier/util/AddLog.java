package com.rainier.util;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AddLog {
    String desc() default "无备注";
    String title() default "无标题";
}
