package com.wy.example.netty.example43;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 利用LineBasedFrameDecoder和StringDecoder解决TCP沾包
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-29  14:27
 **/
public class TimeServerHandler extends ChannelHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        logger.info("The time server receive order:" + body + "; the counter is : " + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //当发生异常，关闭ChannelHandlerContext，释放和ChannelHandlerContext相关联的句柄等资源。
        ctx.close();
    }

}
