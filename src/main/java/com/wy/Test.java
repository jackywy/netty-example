package com.wy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    private static final double EARTH_RADIUS = 6378.137;//地球半径

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static void main(String args[]) {
        logger.info("第一个测试用例");
        Test.getDistance(117.539905, 36.719823, 119.51626, 25.962553);
    }

    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        System.out.println("计算得到两个经纬度之间的距离为:" + s);
        return s;
    }
}
