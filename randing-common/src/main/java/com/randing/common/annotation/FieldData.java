package com.randing.common.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface FieldData {

    /**
     * 名称
     * @return
     */
    String name() default "";

    /**
     * 描述
     * @return
     */
    String description() default "";
}
