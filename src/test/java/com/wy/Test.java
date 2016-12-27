package com.wy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @org.testng.annotations.Test
    public void testFirst() {
        logger.info("第一个测试用例");
    }
}
