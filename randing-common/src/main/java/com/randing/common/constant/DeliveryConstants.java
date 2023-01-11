package com.randing.common.constant;

import com.randing.common.annotation.Pdf;
import org.python.modules.itertools.product;

/**
 * @version 1.0.0
 * @ClassName: DeliveryConstants.java
 * @author: Leen Meng
 * @Description: 运件常量
 * @createTime: 2021年07月21日 15:29:00
 */
public class DeliveryConstants {

    /**
     * 运件明细文件名
     */
    public static final String SHIPMENT_DETAILS_FILE_NAME = "运件明细";

    /**
     * 编号单品
     */
    public static final String NUMBER_SINGLE_PRODUCT = "编号单品";

    /**
     * 未编号单品
     */
    public static final String UNNUMBERED_ITEMS = "未编号单品";

    /**
     * 运件编号
     */
    public static final String DELIVERYNO = "运件编号";

    /**
     * 状态
     */
    public static final String STATUS = "状态";

    /**
     * 物流编号
     */
    public static final String LOGISTICSNUMBER = "物流编号";

    /**
     * 起运地
     */
    public static final String  SOURCE = "起运地";

    /**
     * 目的地
     */
    public static final String  DES = "目的地";


    //已请求
    public static final Integer  Requested = 1;

    //已取消
    public static final Integer  Cancelled = 2;

    //运送中
    public static final Integer  transporting = 3;

    //已丢失
    public static final Integer  Lost = 4;

    //已拒收
    public static final Integer  Rejected = 5;

    //已收货
    public static final Integer  received = 6;

    //普通运件
    public static final Integer  ORDINARY_SHIPMENT = 1;

    //退回运件
    public static final Integer  RETURN_SHIPMENT = 0;
}
