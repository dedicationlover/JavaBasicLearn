package com.annotation.demo2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 获取属性字段---类属性<-->table 字段
 *
 * @author Administrator
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SxtField
{
    String columnName();

    String type();

    int length();
}
