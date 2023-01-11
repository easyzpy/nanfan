package com.randing.system.domain.pdf;

import com.randing.common.annotation.Pdf;
import lombok.Data;

/**
 * @Author patrick
 * @Description 分层因素导出pdf
 * @Date 2021/6/16
 * @Version 1.0
 */
@Data
public class FactorPdf {

    /**
     * 分层因素名称
     */
    @Pdf(name = "名称")
    private String name;

    /**
     * 权重
     */
    @Pdf(name = "权重")
    private Integer weight;

    /**
     * 水平
     */
    @Pdf(name = "水平")
    private String level;
}
