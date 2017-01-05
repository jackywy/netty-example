package com.wy.example.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO服务端演示代码
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-28  11:13
 **/
public class TimeServer {
    private static final Logger logger = LoggerFactory.getLogger(TimeServer.class);

    public static void main(String args[]) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                logger.error("format port error");
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            logger.info("The time server is start in port:" + port);
            Socket socket = null;
            while (true) {
                socket = server.accept();//等待客户端连接8080端口
                new Thread(new TimeServerHandler(socket)).start();//启动线程处理客户端消息，一个客户端连接就要创建一个线程
            }
        } finally {
            if (server != null) {
                logger.info("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
