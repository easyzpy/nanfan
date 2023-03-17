package com.randing.common.core.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseMybatisEntity implements Serializable {

    private static final long serialVersionUID = 1983571054651038514L;
    @TableField
    private LocalDateTime createTime;
    @TableField
    private LocalDateTime updateTime;
}

