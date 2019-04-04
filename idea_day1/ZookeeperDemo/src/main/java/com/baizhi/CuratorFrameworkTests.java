package com.baizhi;

import org.apache.commons.lang.SerializationUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.Date;

public class CuratorFrameworkTests {
    private CuratorFramework curatorFramework=null;
    @Before
    public void before(){
        String servers = "CentOS:2181";
        RetryPolicy retryPolicy =new ExponentialBackoffRetry(1000,5);
                //new RetryNTimes(3,1000);
        curatorFramework=CuratorFrameworkFactory.newClient(servers,retryPolicy);
        curatorFramework.start();
    }
    @Test
    public void testCreate() throws Exception {
        curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .inBackground()
                .forPath("/baizhi","hello".getBytes());
        Thread.sleep(100);
    }
    @Test
    public void testReadfData() throws Exception {
        byte[] bytes = curatorFramework.getData()
                .forPath("/baizhi");
        System.out.println(SerializationUtils.serialize(bytes));
    }
    @Test
    public void testWriteData() throws Exception {
        curatorFramework.setData().forPath("/baizhi", SerializationUtils.serialize(new Date()));
    }
    @Test
    public void testDelete
    @After
    public void after(){
        curatorFramework.close();

    }
}
