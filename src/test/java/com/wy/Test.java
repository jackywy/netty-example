package com.wy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_UP;

public class Test {
    private static final Logger logger = LoggerFactory.getLogger(com.wy.test.Test.class);

    @org.testng.annotations.Test
    public void testFirst() {
        logger.info("第一个测试用例");
        BigDecimal a = new BigDecimal(String.valueOf("123.618"));//成本价
        BigDecimal b = new BigDecimal(String.valueOf("129.63"));//售卖价
        BigDecimal c = a.add(b.subtract(a).divide(new BigDecimal("2")));//计算分销价
        BigDecimal d = c.setScale(0, BigDecimal.ROUND_DOWN);//截取小数点前整形，例126.123=>126 123.62=>126
        BigDecimal e = c.subtract(d);
        BigDecimal f = BigDecimal.ZERO;//正真分销价
        //如果小数后两位大于0.5, 则向上取证，例126.62=>127
        f = c.setScale(2, ROUND_UP);
        if (e.compareTo(new BigDecimal("0.5")) >= 0) {
            f = d.add(BigDecimal.ONE);
        } else {
            f = d.add(new BigDecimal("0.5"));
        }
        logger.info(String.valueOf(a));
        logger.info(String.valueOf(b));
        logger.info(String.valueOf(c));
        logger.info(String.valueOf(f));
    }
}
