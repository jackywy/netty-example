package com.wy.example.netty.example723;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * 编码器
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-30  17:13
 **/
public class MsgpackEncoder extends MessageToByteEncoder<UserInfo> {
    @Override
    protected void encode(ChannelHandlerContext arg0, UserInfo arg1, ByteBuf arg2) throws Exception {
        MessagePack msgpack = new MessagePack();
        msgpack.register(UserInfo.class);
        //Serializable
        byte[] raw = msgpack.write(arg1);
        arg2.writeBytes(raw);
    }
}
