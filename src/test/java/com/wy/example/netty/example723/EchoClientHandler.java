package com.wy.example.netty.example723;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MessagePack编解码测试
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-30  16:34
 **/
public class EchoClientHandler extends ChannelHandlerAdapter {
    public static final Logger logger = LoggerFactory.getLogger(EchoClientHandler.class);


    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        UserInfo[] infos = getUserInfos();
        for (UserInfo infoE : infos) {
            ctx.write(infoE);
        }
        ctx.flush();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("Client receive the msgpack message: " + msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    private UserInfo[] getUserInfos() {
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo = null;
        for (int i = 0; i < sendNumber; i++) {
            userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setUserName("ABCDEFG --->" + i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }
}
