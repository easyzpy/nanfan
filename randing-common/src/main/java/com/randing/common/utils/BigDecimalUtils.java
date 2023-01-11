package com.randing.common.utils;

import java.math.BigDecimal;

/**
 * 时间工具类
 * 
 * @author randing
 */
public class BigDecimalUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }
}
