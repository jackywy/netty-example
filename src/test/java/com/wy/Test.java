package com.wy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @org.testng.annotations.Test
    public void testFirst() {
        logger.info("第一个测试用例");
        BigDecimal a = new BigDecimal(String.valueOf("123.12"));//成本价
        BigDecimal b = new BigDecimal(String.valueOf("129.12"));//售卖价
        BigDecimal c = a.add(b.subtract(a).divide(new BigDecimal("2")));//分销价
        double d = Math.ceil(c.doubleValue());
        logger.info(String.valueOf(d));
    }
}
