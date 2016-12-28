package com.wy.example.paio;/**
 * Created by wangyang15 on 2016/12/28.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 伪异步客户端演示代码
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-28  11:13
 **/
public class TimeClient {
    private static final Logger logger = LoggerFactory.getLogger(TimeClient.class);

    public static void main(String args[]) {
        int port = 8080;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            //客户端发送指令给服务端
            out.println("QUERY TIME ORDER");
            logger.info("Send order to server succeed");
            //接受服务端返回消息
            String resp = in.readLine();
            logger.info("Now is :" + resp);
        } catch (IOException e) {
            //no handler
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
