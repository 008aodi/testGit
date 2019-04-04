package com.baizhi.demo02.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws Exception {
        //1.创建客户端启动引导
        Bootstrap bt=new Bootstrap();
        //2.设置请求转发和响应线程池
        EventLoopGroup worker=new NioEventLoopGroup();
        //3.设置线程池
        bt.group(worker);
        //4.设置服务器实现类
        bt.channel(NioSocketChannel.class);
        //5.初始化通讯管道-重点
        bt.handler(new ClientChannelInitializer());
        //6.设置服务器端口并启动服务器
        ChannelFuture future = bt.connect("127.0.0.1",9999).sync();
        //7.关闭服务器通讯管道
        future.channel().closeFuture().sync();
        //8.释放资源
        worker.shutdownGracefully();
    }
}
