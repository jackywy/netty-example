package com.wy.example.nio;

/**
 * NIO时间服务器客户端TimeClient
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-28  14:52
 **/
public class TimeClient {
    public static void main(String args[]) {
        int port = 8080;
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
    }
}
