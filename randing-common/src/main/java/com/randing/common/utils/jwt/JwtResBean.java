package com.randing.common.utils.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResBean implements Serializable {
    private static final long serialVersionUID = 1467400777840707094L;

    private User nanUser;

    private String token;
}
