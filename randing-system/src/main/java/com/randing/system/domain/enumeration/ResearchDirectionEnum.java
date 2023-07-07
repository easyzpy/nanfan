package com.randing.system.domain.enumeration;

public enum ResearchDirectionEnum {
    A("25", "国家级重大项目"),
    B("22", "省级重点项目"),
    C("20", "地市级或校内项目"),
    D("18", "横向项目或企业自筹项目"),
    E("16", "其他项目"),

    ;


    private final String code;
    private final String desc;

    ResearchDirectionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }
}
