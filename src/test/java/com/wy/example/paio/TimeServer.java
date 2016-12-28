package com.wy.example.paio;/**
 * Created by wangyang15 on 2016/12/28.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步IO服务端演示代码
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-28  13:07
 **/
public class TimeServer {
    private static Logger logger = LoggerFactory.getLogger(TimeServer.class);

    public static void main(String args[]) throws IOException {
        int port = 8080;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            logger.info("The time server is start in port:" + port);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);//创建IO任务线程池
            while (true) {
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
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
