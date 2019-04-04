package com.baizhi.demo01.client;

import io.netty.buffer.ByteBuf;
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
        byte[] bytes = "你好,我是客户端!".getBytes();
        ByteBuf byteBuf= ctx.alloc().buffer();
        byteBuf.writeBytes(bytes);

        ctx.writeAndFlush(byteBuf);
    }

    //接收服务器响应
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf= (ByteBuf) msg;
        System.out.println("客户端收到:"+buf.toString(CharsetUtil.UTF_8));
    }
}
