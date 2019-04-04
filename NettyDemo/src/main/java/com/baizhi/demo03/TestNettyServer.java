package com.baizhi.demo03;

import com.baizhi.demo03.NettyServer;

import java.io.IOException;

public class TestNettyServer {
    public static void main(String[] args) throws IOException {
        NettyServer nettyServer=new NettyServer();
        nettyServer.start(new HostAndPort("127.0.0.1", 8888),(Object cmd) -> {return "哈喽 "+cmd;});

        System.in.read();
        nettyServer.close();
    }
}
