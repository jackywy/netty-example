package com.wy.gps.distance;

import java.math.BigDecimal;

/**
 * 经纬度ddd转换成dms
 */
public class Ddd2Dms {
    private static final Double lngDDD = 108.90593;//ddd经度
    private static final Double latDDD = 34.21630;//ddd维度

    public static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(lngDDD);
        BigDecimal b = BigDecimal.valueOf(lngDDD.intValue());
        BigDecimal c = a.subtract(b);
        System.out.println("获取经度小数位数字" + c);
        BigDecimal minute = c.multiply(new BigDecimal("60"));
        BigDecimal minuteDecimal = minute.subtract(BigDecimal.valueOf(minute.intValue()));
        System.out.println("获取经度DMS分" + minute.intValue());
        BigDecimal second = minuteDecimal.multiply(new BigDecimal("60"));
        System.out.println("获取经度DMS秒" + second.intValue());
    }
}
