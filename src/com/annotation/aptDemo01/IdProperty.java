package com.annotation.aptDemo01;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface IdProperty {
    String column();

}
