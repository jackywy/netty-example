package com.wy.example.netty.example52;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FixedLengthFrameDecoder服务端开发
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-29  19:13
 **/
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelHandlerAdapter {
    public static final Logger logger = LoggerFactory.getLogger(EchoServerHandler.class);
    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        logger.info("This is " + ++counter + " times receive client:  [" + body + "]");
        body += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(echo);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();//发生异常，关闭链路
    }
}
