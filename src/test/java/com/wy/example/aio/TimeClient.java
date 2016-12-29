package com.wy.example.aio;

/**
 * AIO时间服务器客户端
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-29  10:48
 **/
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        new Thread(new AsyncTimeClientHandler("127.0.0.1", port), "AIO-AsyncTimeClientHandler-001").start();
    }
}
