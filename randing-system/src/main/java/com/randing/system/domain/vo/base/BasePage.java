package com.randing.system.domain.vo.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePage implements Serializable {

    private static final long serialVersionUID = -6207207767714132726L;
    @TableField(exist = false)
    protected Long page;
    @TableField(exist = false)
    protected Long pageSize;

    protected Long id;


}
