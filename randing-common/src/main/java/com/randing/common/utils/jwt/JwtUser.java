package com.randing.common.utils.jwt;

import com.randing.common.utils.iface.dto.YzbUserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUser implements Serializable {
    private static final long serialVersionUID = 7789540131797529083L;
//    private String userId;
//    private String name;
//    private String username;

    private String yzbToken;

    private String refreshKey;

    private YzbUserInfo userInfo;

    private LocalDateTime iat;
    private LocalDateTime exp;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.exp);
    }

    public boolean isExpiredHalf() {
        Duration jwtDuration = Duration.between(this.iat, this.exp);
        Duration nowDuration = Duration.between(LocalDateTime.now(), this.exp);
        return nowDuration.toMillis() < jwtDuration.toMillis() / 2L;
    }

}
