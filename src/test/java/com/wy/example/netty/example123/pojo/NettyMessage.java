package com.wy.example.netty.example123.pojo;

/**
 * Netty协议栈开发
 *
 * @author Jacky
 * @version 1.0
 * @create 2017-01-06  11:22
 **/
public class NettyMessage {
    private Header header;
    private Object body;


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
