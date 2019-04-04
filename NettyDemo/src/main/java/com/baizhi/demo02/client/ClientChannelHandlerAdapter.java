package com.baizhi.demo02.client;

import com.baizhi.demo02.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class ClientChannelHandlerAdapter  extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.err.println("错了:"+cause.getMessage());
    }
    //发送数据给服务器
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelFuture future = ctx.writeAndFlush(new User());
        //注册序列化异常处理
        future.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        //序列化出错关闭连接
        future.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }

    //接收服务器响应
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("客户端收到:"+msg);
    }
}
