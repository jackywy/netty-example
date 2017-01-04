package com.wy.example.netty.example9;

import com.wy.example.netty.example812.protobuf.SubscribeReqProto;
import com.wy.example.netty.example812.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Marshalling版本图书订购代码
 *
 * @author Jacky
 * @version 1.0
 * @create 2017-01-04  15:11
 **/
@ChannelHandler.Sharable
public class SubReqServerHandler extends ChannelHandlerAdapter {

    public static final Logger logger = LoggerFactory.getLogger(SubReqServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        logger.info("Server accept client subscribe req:[" + req.toString() + "]");
        ctx.writeAndFlush(resp(req.getSubReqID()));
    }

    private SubscribeRespProto.SubscribeResp resp(int subReqID) {
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(subReqID);
        builder.setRespCode(0);
        builder.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
