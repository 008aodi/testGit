package com.baizhi.demo03;

import com.baizhi.demo03.common.CustomMessageToMessageDecoder;
import com.baizhi.demo03.common.CustomMessageToMessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;


public class NettyServer {
    private ServerBootstrap sbt=new ServerBootstrap();

    private EventLoopGroup boss=new NioEventLoopGroup();
    private EventLoopGroup worker=new NioEventLoopGroup();

    public  NettyServer(){
        sbt.group(boss,worker);
        sbt.channel(NioServerSocketChannel .class);
    }

    public void start(final HostAndPort hostAndPort, final CmdProcessor cmdProcessor){
        Thread thread = new Thread() {
            @Override
            public void run() {
                sbt.childHandler(new ChannelInitializer<SocketChannel>() {
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

                            //接收客户端请求,并给出响应
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object cmd) throws Exception {
                                Object result=cmdProcessor.process(cmd);
                                ChannelFuture future = ctx.writeAndFlush(result);
                                //关闭通道
                                future.addListener(ChannelFutureListener.CLOSE);
                                //注册序列化异常处理
                                future.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
                                //序列化出错关闭连接
                                future.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                            }
                        });
                    }
                });
                try {
                    ChannelFuture future = sbt.bind(hostAndPort.getHost(),hostAndPort.getPort()).sync();
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e.getCause());
                }
            }
        };
        //设置线程为-守护main线程
        thread.setDaemon(true);
        thread.start();
    }


    public void close(){
          boss.shutdownGracefully();
          worker.shutdownGracefully();
    }
}
