package com.randing.system.domain.vo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePage implements Serializable {

    private static final long serialVersionUID = -6207207767714132726L;

    protected Long page;

    protected Long pageSize;

    protected Long id;


}
