package com.annotation.demo1;

import java.lang.annotation.*;

/**
 * 另一个修饰普通成员变量的Annotation
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface Property
{
    String column();
    String type();
}
