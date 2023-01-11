package com.randing.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface InspectionLogger {

    /**
     * 操作类型:  Add，Update，Delete
     */
    String type() default "";

    /**
     * 操作模块:1-项目，2-申办方，3-研究中心，4-中心实验室，
     *         5-仓库，6-药品，7-单品，8-运件，,9-访视,
     *         10-仓库药品 11-研究中心药品 12-数据更正
     */
    String module() default "";

    /**
     * 操作描述
     */
    String requestDes() default "";

    /**
     * 需要查询的id
     */
    String parameterKey() default "";

    /**
     * 单个查询的方法
     */
    String queryMethod() default "";

    /**
     * 查询的bean名称
     */
    String serviceClass() default "";




}
