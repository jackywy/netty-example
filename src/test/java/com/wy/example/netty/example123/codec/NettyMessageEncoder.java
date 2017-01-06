package com.wy.example.netty.example123.codec;

import com.wy.example.netty.example123.pojo.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.util.Map;

/**
 * Netty协议栈开发
 *
 * @author Jacky
 * @version 1.0
 * @create 2017-01-06  11:34
 **/
public final class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {
    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException {
        this.marshallingEncoder = new MarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf out) throws Exception {
        if (msg == null || msg.getHeader() == null) {
            throw new Exception("The encode message is null");
        }
        ByteBuf sendBuff = Unpooled.buffer();
        sendBuff.writeInt(msg.getHeader().getCrcCode());
        sendBuff.writeInt(msg.getHeader().getLength());
        sendBuff.writeLong(msg.getHeader().getSessionID());
        sendBuff.writeByte(msg.getHeader().getType());
        sendBuff.writeByte(msg.getHeader().getPriority());
        sendBuff.writeInt(msg.getHeader().getAttachment().size());
        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuff.writeInt(keyArray.length);
            sendBuff.writeBytes(keyArray);
            value = param.getValue();
            marshallingEncoder.encode(value, sendBuff);
        }
        key = null;
        keyArray = null;
        value = null;
        if (msg.getBody() != null) {
            marshallingEncoder.encode(msg.getBody(), sendBuff);
        } else {
            sendBuff.writeInt(0);
            sendBuff.setInt(4, sendBuff.readableBytes());
        }


    }


}
