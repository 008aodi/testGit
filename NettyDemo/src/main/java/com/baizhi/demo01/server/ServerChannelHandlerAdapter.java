package com.baizhi.demo01.server;

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
        ByteBuf byteBuf= (ByteBuf) msg;
        System.out.println("服务器收到:"+byteBuf.toString(CharsetUtil.UTF_8));
        byte[] bytes = new Date().toString().getBytes();
        ByteBuf buf=ctx.alloc().buffer();
        buf.writeBytes(bytes);

        ChannelFuture future = ctx.writeAndFlush(buf);
        //关闭通道
        future.addListener(ChannelFutureListener.CLOSE);
    }

}
