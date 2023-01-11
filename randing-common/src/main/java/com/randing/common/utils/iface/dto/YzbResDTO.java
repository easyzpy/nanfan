package com.randing.common.utils.iface.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YzbResDTO<T> implements Serializable {

    private static final long serialVersionUID = 844930051727735315L;
    /**
     * 请求状态
     */
    private String success;

    /**
     * 状态码
     */
    private String code;
    /**
     * 描述
     */
    private String msg;

    private T data;
}
