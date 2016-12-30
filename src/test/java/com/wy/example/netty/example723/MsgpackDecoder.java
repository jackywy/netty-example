package com.wy.example.netty.example723;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * 解码器
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-30  17:00
 **/
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext arg0, ByteBuf arg1, List<Object> arg2) throws Exception {
        final byte[] array;
        final int length = arg1.readableBytes();
        array = new byte[length];
        arg1.getBytes(arg1.readerIndex(), array, 0, length);
        MessagePack msgPack = new MessagePack();
        arg2.add(msgPack.read(array));
    }
}
