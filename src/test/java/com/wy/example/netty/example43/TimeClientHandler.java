package com.wy.example.netty.example43;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 利用LineBasedFrameDecoder和StringDecoder解决TCP沾包
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-29  14:59
 **/
public class TimeClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TimeClientHandler.class);
    private int counter;
    private byte[] req;

    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        logger.info("Now is:" + body + " ;the counter is : " + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //释放资源
        logger.warn("Unexpected exception from downstream:" + cause.getMessage());
        ctx.close();
    }
}
