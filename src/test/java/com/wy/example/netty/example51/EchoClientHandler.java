package com.wy.example.netty.example51;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DelimiterBasedFrameDecoder服务端开发
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-29  19:28
 **/
public class EchoClientHandler extends ChannelHandlerAdapter {
    public static final Logger logger = LoggerFactory.getLogger(EchoClientHandler.class);

    private int counter;
    static final String ECHO_REQ = "Hi, wy, wlecome to Netty.$_";

    public EchoClientHandler() {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("This is " + ++counter + " times receive server: [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
