package com.wy.example.netty.example812;

import com.google.protobuf.InvalidProtocolBufferException;
import com.wy.example.netty.example812.protobuf.SubscribeReqProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Protobuf入门
 *
 * @author Jacky
 * @version 1.0
 * @create 2017-01-04  14:09
 **/
public class TestSubscribeReqProto {
    private static final Logger logger = LoggerFactory.getLogger(TestSubscribeReqProto.class);

    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribleReq() {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("WangYang");
        builder.setProductName("Netty Book");
        ArrayList<String> address = new ArrayList<String>();
        address.add("NanJing YuHuaTai");
        address.add("BeiJing LiuLiChang");
        address.add("ShenZhen HongShuLin");
        builder.addAllAddress(address);
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribleReq();
        logger.info("Before encode: " + req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        logger.info("After decode: " + req.toString());
        logger.info("Assert  equal: -->" + req2.equals(req));
    }
}
