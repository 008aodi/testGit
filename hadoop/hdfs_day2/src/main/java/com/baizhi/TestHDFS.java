package com.baizhi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class TestHDFS {
    private FileSystem fileSystem;
    private Configuration conf;
    @Before
    public void before() throws IOException {
        conf= new Configuration();
        conf.addResource("core-size.xml");
        conf.addResource("hdfs-size.xml");
        fileSystem=FileSystem.newInstance(conf);
    }
    @Test
    public void TestConfig(){
        String value = conf.get("dfs.replication");
        System.out.println(value);
    }
    @Test
    public void TestUpload1() throws IOException {
        String file = "D:\\t_access.txt";
        Path dst = new Path("/demo/access/t_access.txt");
        InputStream is = new FileInputStream(file);
        OutputStream os = fileSystem.create(dst);
        /*OutputStream os = fileSystem.create(dst, new Progressable() {
            @Override
            public void progress() {
                System.out.print(".");
            }
        });*/
        IOUtils.copyBytes(is,os,1024,true);
    }
    @Test
    public void TestUpload2() throws IOException {
        Path src = new Path("C:\\Users\\Administrator\\Desktop\\笔记.pdf");
        Path dst = new Path("/demo/access/笔记1.pdf");
        fileSystem.copyFromLocalFile(src,dst);
    }
    @Test
    public void testDownload1() throws IOException {
        String file = "C:\\Users\\Administrator\\Desktop\\笔记1.pdf";
        Path dst = new Path("/demo/access/springBoot.pdf");
        OutputStream os = new FileOutputStream(file);
        InputStream is= fileSystem.open(dst);
        IOUtils.copyBytes(is,os,1024,true);
    }
    @Test
    public void testDownload2() throws IOException {
        Path src = new Path("C:\\Users\\Administrator\\Desktop\\笔记1.pdf");
        Path dst = new Path("/demo/access/springBoot.pdf");
        fileSystem.copyFromLocalFile(src,dst);
    }
    @Test
    public void testDelete() throws IOException {
        Path src = new Path("/demo");
        fileSystem.deleteOnExit(src);
    }
    @Test
    public void testExtist() throws IOException {
        Path src = new Path("/demo/access/笔记.pdf");
        boolean exists = fileSystem.exists(src);
        assertTrue(exists);
    }
    @Test
    public void testMKdir() throws IOException {
        Path src = new Path("/demo/access");
        boolean exists = fileSystem.exists(src);
        if(!exists){
            fileSystem.mkdirs(src);
        }
    }
    @Test
    public void testListFiles() throws IOException {
        Path src = new Path("/");
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(src, true);
        while(files.hasNext()){
            LocatedFileStatus file = files.next();
            System.out.println(file.getPath()+"|"+file.isFile()+"|"+file.getLen());
            BlockLocation[] blockLocations = file.getBlockLocations();
            for (BlockLocation location : blockLocations) {
                System.out.println(location.getOffset()+"|"+location.getLength());
            }
        }
    }
    @Test
    public void testDeleteWithTrash() throws IOException {
        Trash trash = new Trash(fileSystem,conf);
        Path path = new Path("/demo/access/笔记.pdf");
        trash.moveToTrash(path);
    }
    @After
    public void after() throws IOException {
        fileSystem.close();
    }
}
