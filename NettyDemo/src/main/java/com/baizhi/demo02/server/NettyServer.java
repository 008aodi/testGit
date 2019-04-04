package com.baizhi.demo02.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws Exception {
        //1.创建服务启动引导
        ServerBootstrap sbt=new ServerBootstrap();
        //2.设置请求转发和响应线程池
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();
        //3.设置线程池
        sbt.group(boss,worker);
        //4.设置服务器实现类
        sbt.channel(NioServerSocketChannel.class);
        //5.初始化通讯管道-重点
        sbt.childHandler(new ServerChannelInitializer());
        //6.设置服务器端口并启动服务器
        System.out.println("我是服务器....");
        ChannelFuture future = sbt.bind(9999).sync();
        //7.关闭服务器通讯管道
        future.channel().closeFuture().sync();
        //8.释放资源
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
}
