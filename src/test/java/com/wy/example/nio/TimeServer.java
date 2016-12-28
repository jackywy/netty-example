package com.wy.example.nio;

/**
 * Nio时间服务器TimeServer
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-28  14:29
 **/
public class TimeServer {
    public static void main(String args[]) {
        int port = 8080;
        //设置监听端口
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        //创建了一个被称为MutiplexerTimeServer的多路复用类，它是一个独立的线程，负责轮询多路复用器Selector，可以处理多个客户端的并发接入。
        new Thread(timeServer, "NIO-MutiplexerTimeServer-001").start();
    }
}
