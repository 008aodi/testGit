package com.baizhi.demo02.client;

import com.baizhi.demo02.common.CustomMessageToMessageDecoder;
import com.baizhi.demo02.common.CustomMessageToMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //添加数据帧解码器
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
        //添加对象帧编码器
        pipeline.addLast(new LengthFieldPrepender(2));
        //添加对象解码器
        pipeline.addLast(new CustomMessageToMessageDecoder());
        //添加对象编码器
        pipeline.addLast(new CustomMessageToMessageEncoder());
        //添加最终处理者
        pipeline.addLast(new ClientChannelHandlerAdapter());
    }
}
