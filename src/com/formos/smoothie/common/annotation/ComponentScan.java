package com.formos.smoothie.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ComponentScans.class)
public @interface ComponentScan {

    public String value();
}
