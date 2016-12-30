package com.wy.example.netty.example723;

import java.io.Serializable;

/**
 * MessagePack编解码测试
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-30  14:06
 **/
public class UserInfo implements Serializable {
    /**
     * 默认的序列号
     */
    public static final long serialVersionUID = 1L;

    private String userName;
    private int userID;
    private int age;

    public UserInfo buildUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserInfo buildUserID(int userID) {
        this.userID = userID;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
