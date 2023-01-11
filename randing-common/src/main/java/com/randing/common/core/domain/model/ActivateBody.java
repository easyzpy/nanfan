package com.randing.common.core.domain.model;

import lombok.Data;

/**
 * @version 1.0.0
 * @ClassName: ActivateBody.java
 * @author: Leen Meng
 * @Description: 激活参数
 * @createTime: 2021年06月22日 14:29:00
 */
@Data
public class ActivateBody {

    private Long id;
    private String name;
    private String pwd;
    private String code;

    public ActivateBody() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ActivateBody(Long id, String name, String pwd, String code) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.code = code;
    }
}
