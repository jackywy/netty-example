package com.wy.example.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author Jacky
 * @version 1.0
 * @create 2016-12-29  10:26
 **/
public class AcceptCompletionHandler implements CompletionHandler<java.nio.channels.AsynchronousSocketChannel, AsyncTimeServerHandler> {
    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        //调用accept方法后，如果有新的客户端连接接入，系统将回调我们传入的CompletionHandler的completed方法，表示新的客户端已经接入成功。
        //因为一个AsynchronousServerSocketChannel的accpet可以接受成千上万个客户端。
        attachment.asynchronousServerSocketChannel.accept(attachment,this);
        //创建新的ByteBuffer，预分配1MB的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //进行异步读操作
        /**
         * ByteBuffer dst: 接受缓冲区，用于从异步Channel中读取数据包
         * A attachment: 异步Channel携带的附件，通知回调的时候作为入参使用
         * CompletionHandler: 接受通知回调的业务Handler
         */
        result.read(buffer,buffer,new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        attachment.latch.countDown();
    }
}
