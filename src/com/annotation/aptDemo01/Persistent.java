package com.annotation.aptDemo01;

import java.lang.annotation.*;

/**
 * 仅在class文件中保留，运行时不能通过反射来读取该Annotation信息
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Persistent {
    String table();
}
