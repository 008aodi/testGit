package com.baizhi.demo02.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class ServerChannelHandlerAdapter extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("错了:"+cause.getMessage());
    }

    //接收客户端请求,并给出响应
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器收到:"+msg);

        ChannelFuture future = ctx.writeAndFlush(new Date());
        //关闭通道
        future.addListener(ChannelFutureListener.CLOSE);
        //注册序列化异常处理
        future.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        //序列化出错关闭连接
        future.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }

}
