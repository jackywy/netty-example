package com.wy.example.aio;

/**
 * AIO时间服务器服务端
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-29  10:15
 **/
public class TimeServer {


    public static void main(String[] args) {
        int port = 8080;
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsynTimeServerHandler-001").start();
    }
}
