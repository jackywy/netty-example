package com.wy.example.nio;/**
 * Created by wangyang15 on 2016/12/28.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * nio时间服务器
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-28  13:54
 **/
public class MultiplexerTimeServer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MultiplexerTimeServer.class);
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     * 在构造方法上进行资源初始化。创建多路复用器Selector、ServerSocketChannel，对Channel和TCP参数进行配置。
     * 例如，将ServerSocketChannel设置为异步非阻塞模式，它的backlog设为1024。
     * 系统资源初始化成功后，将ServerSocketChannel注册到Selector,监听SelectionKey.OP_ACCEPT操作位。
     * 如果资源初始化失败，例如端口被占用，则退出。
     *
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            logger.info("The time server is start in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    /**
     * while循环体中遍历Selector，它的休眠时间为1s。
     * 无论是否有读写等事件发生，selector每隔1s都被唤醒一次。
     * Selector也提供了一个无参的select方法，当有处于就绪状态的Channel时，selector将返回该Channel的selectionKey集合。
     * 通过对就绪状态的Channel集合进行迭代，可以进行网络的异步读写操作。
     */
    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.channel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        //多路复用器关闭后，所有注册在上面的Channel 和 Pipe  等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            //处理新接入的消息
            /**
             * 根据SelectionKey的操作位进行判断即可获知网络时间的类型，通过ServerSocketChannel的accept接受客户端的连接请求并创建SocketChannel实例。
             * 完成上述操作后，相当于完成TCP的三次握手，TCP物理链路正式建立。
             * 注意：我们需要将新创建的SocketChannel设置为异步非阻塞，同时也可以对其TCP参数进行设置，例如TCP接收和发送缓冲区的大小等。
             */
            if (key.isAcceptable()) {
                //Accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //Add the new connection to the selector
                sc.register(selector, SelectionKey.OP_ACCEPT);
            }
            /**
             * 用于读取客户端的请求消息。
             * 首先创建一个ByteBuffer，由于我们事先无法得知客户端发送的码流大小，作为例程，我们开辟一个1MB的缓冲区。
             * 然后调用SocketChannel的read方法读取请求码流。
             * 注意：由于我们已经将SocketＣhannel设置为异步非阻塞模式，因此它的read是非阻塞的。
             * 使用返回值进行判断，看读取到的字节数，返回值有三种可能的接口。
             */
            if (key.isReadable()) {
                //Read the data
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                //返回值大于0，读取到字节，对字节进行编解码。
                if (readBytes > 0) {
                    //将缓冲区当前的limit设置为postion，position设置为0.用于后续对缓冲区的读取操作。
                    readBuffer.flip();
                    //然后根据缓冲区可读的字节个数创建字节数组
                    byte[] bytes = new byte[readBuffer.remaining()];
                    //调用ByteBuffer的get操作将缓冲区可读的字节数组复制到新创建的字节数组中。
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    //打印消息体
                    logger.info("The time server receive order:" + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    //把服务器当前时间返回给客户端
                    doWrite(sc, currentTime);
                } else if (readBytes < 0) {//返回值小于0，链路已经关闭，需要关闭SocketChannel，释放资源
                    //对端链路关闭
                    key.channel();
                    sc.close();
                } else {
                    //读到0字节，忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
