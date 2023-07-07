package com.randing.system.domain.enumeration;

public enum RentEnum {
    LR("长租")
   ,SR("短租")
    ,;
    private String desc;

    RentEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
