package com.randing.common.utils.iface.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTokenResDTO implements Serializable {

    private static final long serialVersionUID = -2405816743099874818L;
    /**
     * token
     */
    private String accessToken;
    /**
     * refreshKey
     */
    private String refreshKey;
}
