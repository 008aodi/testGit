package com.baizhi.demo03;

import java.util.Date;

public class TestNettyClient {
    public static void main(String[] args)  {
        NettyClient client=new NettyClient();
        Object returnValue = client.call("张三",
                new HostAndPort("127.0.0.1", 8888));
        client.close();
        System.out.println(returnValue);
    }
}
