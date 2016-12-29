package com.wy.example.netty.example42;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 未考虑TCP沾包导致功能异常案例
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-29  14:50
 **/
public class TimeClient {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        new TimeClient().connect("127.0.0.1", port);
    }

    public void connect(String host, int port) throws Exception {
        //配置客户端NIO线程组
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });
            //发起异步连接操作
            ChannelFuture f = b.connect(host, port).sync();
            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        } finally {
//             优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }
}
