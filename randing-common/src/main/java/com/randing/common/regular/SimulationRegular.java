package com.randing.common.regular;

/**
 * @Author patrick
 * @Description 模拟随机正则
 * @Date 2021/6/11
 * @Version 1.0
 */
public class SimulationRegular {

    /**
     * 受试者数量 1-2500
     */
    public static String SUBJECT_NUM_REGULAR="^([1-9]\\d{0,2}|[1-2][0-4]\\d{0,2}|2500)$";

    /**
     * 运行次数 1-5000
     */
    public static String RUN_NUM_REGULAR="^([1-9]\\d{0,2}|[1-4]\\d{0,3}|5000)$";

    /**
     * 受试者与运行次数的乘积 1-25000
     */
    public static String SUBJECT_AND_RUN_PRODUCT_REGULAR="^([1-9]\\d{0,3}|[1-2][0-4]\\d{0,3}|25000)$";

    /**
     * 正整数
     */
    public static String POSITIVE_INTEGER_REGULAR="^[1-9]+[0-9]*$";

    /**
     * 参考概率
     */
    public static String REF_RATE="^(\\d{0,2}|100)$";

}
