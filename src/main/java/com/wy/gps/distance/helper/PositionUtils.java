package com.wy.gps.distance.helper;

import com.wy.gps.distance.domain.DMS;
import com.wy.gps.distance.domain.PositionDMS;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ROUND_CEILING;
import static java.math.BigDecimal.ROUND_DOWN;
import static java.math.BigDecimal.ROUND_UP;

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
    public static DMS covertDDDToDMS(Double ddd) {
        //获取度的小数部分
        BigDecimal degreesDecimal = BigDecimal.valueOf(ddd).subtract(BigDecimal.valueOf(ddd.intValue()));
        //获取带小数位的分
        BigDecimal minute = degreesDecimal.multiply(new BigDecimal("60"));
        //获取分的小数部分
        BigDecimal minuteDecimal = minute.subtract(BigDecimal.valueOf(minute.intValue()));
        //获取带小数位的秒
        BigDecimal second = minuteDecimal.multiply(new BigDecimal("60"));
        return new DMS(ddd.intValue(), minute.intValue(), second.doubleValue());
    }

    /**
     * 度分秒(DMS)转换成度(DDD)
     *
     * @param dms
     * @return
     */
    public static Double covertDMSToDDD(DMS dms) {
        BigDecimal a = BigDecimal.valueOf(dms.getMinutes());
        BigDecimal aa = a.divide(new BigDecimal("60"), 6, ROUND_DOWN);
        BigDecimal b = BigDecimal.valueOf(dms.getSeconds());
        BigDecimal bb = b.divide(new BigDecimal("3600"), 6, ROUND_UP);
        return BigDecimal.valueOf(dms.getDegrees()).add(aa).add(bb).doubleValue();
    }

    /**
     * 获取一组相似经纬度位置列表
     *
     * @param positionDMSList
     * @return
     */
    public static List<PositionDMS> getFilterDMSPositionList(List<PositionDMS> positionDMSList) {
        List<PositionDMS> filterPositionDMSList = new ArrayList<PositionDMS>();
        for (PositionDMS positionDMS : positionDMSList) {
            if (CollectionUtils.isEmpty(filterPositionDMSList)){
                filterPositionDMSList.add(positionDMS);
            }else{

            }
        }
        filterPositionDMSList = positionDMSList;
        return filterPositionDMSList;
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        //度转转换度分秒
        final Double lngDDD = 117.539905;//ddd经度
        final Double latDDD = 36.719823;//ddd维度
        System.out.println(PositionUtils.covertDDDToDMS(lngDDD));
        System.out.println(PositionUtils.covertDDDToDMS(latDDD));

        //度分秒转换度
        System.out.println(PositionUtils.covertDMSToDDD(new DMS(36, 43, 61.3628)));
        System.out.println(PositionUtils.covertDMSToDDD(new DMS(117, 32, 73.658)));


        List<PositionDMS> positionDMSList = new ArrayList<PositionDMS>();
        positionDMSList.add(new PositionDMS(covertDDDToDMS(117.539905), covertDDDToDMS(36.719823)));
        positionDMSList.add(new PositionDMS(covertDDDToDMS(117.539905), covertDDDToDMS(36.88649)));
        List<PositionDMS> filterDMSPositionList = getFilterDMSPositionList(positionDMSList);
        System.out.println("获得过滤后的一组相似列表长度为:" + filterDMSPositionList.size());

    }
}
