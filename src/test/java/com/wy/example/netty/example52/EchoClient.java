package com.wy.example.netty.example52;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * FixedLengthFrameDecoder服务端开发
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-29  19:20
 **/
public class EchoClient {
    //配置客户端NIO线程组
    public void connect(String host, int port) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                    ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new EchoClientHandler());
                }
            });
            //发起异步连接操作
            ChannelFuture f = b.connect(host, port).sync();
            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        } finally {
            //优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new EchoClient().connect("127.0.0.1", port);
    }
}
