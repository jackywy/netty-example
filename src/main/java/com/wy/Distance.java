package com.wy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 计算两两组合之间的距离
 */
public class Distance {
    private static final Logger logger = LoggerFactory.getLogger(Distance.class);

    private static final double EARTH_RADIUS = 6378.137;//地球半径

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static void main(String args[]) {
        //经度相差0.1度--大概相差8千米
        Distance.getDistance(117.4, 36.8, 117.5, 36.8);
        Distance.getDistance(117.6, 36.8, 117.5, 36.8);
        //维度相差0.1度--大概相差11千米
        Distance.getDistance(117.5, 36.7, 117.5, 36.8);
        Distance.getDistance(117.5, 36.9, 117.5, 36.8);
        //经纬度各差0.1度--大概相差14千米
        Distance.getDistance(117.4, 36.7, 117.5, 36.8);
        Distance.getDistance(117.6, 36.7, 117.5, 36.8);
        Distance.getDistance(117.4, 36.9, 117.5, 36.8);
        Distance.getDistance(117.6, 36.9, 117.5, 36.8);

        //维度相差50秒--大概相差1千米
        Distance.getDistance(117.539905, 36.719823, 117.539905, 36.733712);
        //经度相差50秒--大概相差1千米
        Distance.getDistance(117.539905, 36.719823, 117.553794, 36.719823);
        //经纬度各差50秒--大概相差1千米
        Distance.getDistance(117.539905, 36.719823, 117.553794, 36.733712);
    }

    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10;
        System.out.println("计算得到两个经纬度之间的距离为:" + s + "米");
        return s;
    }


}
