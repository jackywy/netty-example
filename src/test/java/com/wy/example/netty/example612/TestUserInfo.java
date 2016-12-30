package com.wy.example.netty.example612;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Java序列化测试
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-30  14:18
 **/
public class TestUserInfo {
    public static final Logger logger = LoggerFactory.getLogger(TestUserInfo.class);

    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.buildUserID(100).buildUserName("Welcome to Netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userInfo);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        logger.info("The jdk serializable length is: " + b.length);
        bos.close();
        logger.info("----------------------------------------");
        logger.info("The byte  array serializable length is : " + userInfo.codeC().length);
    }
}
