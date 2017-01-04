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
public class MsgpackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext arg0, Object arg1, ByteBuf arg2) throws Exception {
        MessagePack msgpack = new MessagePack();
        //Serializable
        byte[] raw = msgpack.write(arg1);
        arg2.writeBytes(raw);
    }
}
