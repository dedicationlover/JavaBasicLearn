package com.annotation.demo1;

import java.lang.annotation.*;

/**
 * 标识属性的
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface IdProperty
{
    String column();
    String type();
    String generator();
}
