package com.wy.example.netty.example723;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * MessagePack编解码测试
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-29  19:03
 **/
public class EchoServer {
    public void bind(int port) throws Exception {
        //配置服务端的NIO线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel sc) throws Exception {
                    sc.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
                    sc.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                    sc.pipeline().addLast(new EchoServerHandler());
                }
            });
            //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new EchoServer().bind(port);
    }
}
