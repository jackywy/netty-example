package com.wy.gps.distance.util;

import com.wy.gps.distance.domain.DmsPosition;

import java.math.BigDecimal;

/**
 * 位置工具类
 */
public class PositionUtils {

    /**
     * 度(DDD)转换成度分秒(DMS)
     *
     * @param ddd
     * @return
     */
    public static DmsPosition covertDDDToDMS(Double ddd) {
        //获取度的小数部分
        BigDecimal degreesDecimal = BigDecimal.valueOf(ddd).subtract(BigDecimal.valueOf(ddd.intValue()));
        //获取带小数位的分
        BigDecimal minute = degreesDecimal.multiply(new BigDecimal("60"));
        //获取分的小数部分
        BigDecimal minuteDecimal = minute.subtract(BigDecimal.valueOf(minute.intValue()));
        //获取带小数位的秒
        BigDecimal second = minuteDecimal.multiply(new BigDecimal("60"));
        return new DmsPosition(ddd.intValue(), minute.intValue(), second.intValue());
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        final Double lngDDD = 108.90593;//ddd经度
        final Double latDDD = 34.21630;//ddd维度
        System.out.println(PositionUtils.covertDDDToDMS(lngDDD));
        System.out.println(PositionUtils.covertDDDToDMS(latDDD));
    }
}
