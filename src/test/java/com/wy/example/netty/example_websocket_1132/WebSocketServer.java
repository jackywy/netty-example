package com.wy.example.netty.example_websocket_1132;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * WebSocket服务端启动类
 *
 * @author Jacky
 * @version 1.0
 * @create 2017-01-05  17:18
 **/
public class WebSocketServer {
    public void run(int port) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //将请求和应答编码或者解码为Http消息
                            ch.pipeline().addLast("http-codec", new HttpServerCodec());
                            //将http消息的多个部分组合成一条完整的http消息
                            ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
                            //支持浏览器和服务端进行websocket通信
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            //增加websocket服务端handler
                            ch.pipeline().addLast("handler", new WebSocketServerHandler());
                        }
                    });
            Channel ch = b.bind(port).sync().channel();
            System.out.println("Web Socket server started at port: " + port);
            System.out.println("Open your browser and navigate to http://localhost:" + port + "/");
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new WebSocketServer().run(8080);
    }
}
