package com.wy.example.netty.example723;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * MessagePack编解码测试
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-30  16:27
 **/
public class EchoClient {
    private final String host;
    private final int port;

    private final int sendNumber;

    public EchoClient(String host, int port, int sendNumber) {
        this.host = host;
        this.port = port;
        this.sendNumber = sendNumber;
    }

    public void run() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
                    ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                    ch.pipeline().addLast(new EchoClientHandler(sendNumber));
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
        new EchoClient("127.0.0.1", 8080, 100).run();
    }
}
