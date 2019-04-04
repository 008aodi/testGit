package com.baizhi.demo03;

import com.baizhi.demo03.common.CustomMessageToMessageDecoder;
import com.baizhi.demo03.common.CustomMessageToMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import java.util.ArrayList;
import java.util.List;

public class NettyClient {
    private Bootstrap bt=new Bootstrap();
    private EventLoopGroup worker=new NioEventLoopGroup();
    public NettyClient(){
        bt.group(worker);
        bt.channel(NioSocketChannel .class);
    }
    public Object call(final Object cmd,HostAndPort hostPort) {
        final List<Object> resultHolder=new ArrayList<Object>();
        bt.handler(new ChannelInitializer<SocketChannel>(){

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
                pipeline.addLast(new ChannelHandlerAdapter(){
                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        System.err.println("错了:"+cause.getMessage());
                    }
                    //发送数据给服务器
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        ChannelFuture future = ctx.writeAndFlush(cmd);
                        //注册序列化异常处理
                        future.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
                        //序列化出错关闭连接
                        future.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                    }

                    //接收服务器响应
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        resultHolder.add(msg);
                    }
                });
            }
        });
        try {
            ChannelFuture future = bt.connect(hostPort.getHost(),hostPort.getPort()).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getCause());
        }
        return resultHolder.get(0);
    }
    public void close(){
        worker.shutdownGracefully();
    }
}

