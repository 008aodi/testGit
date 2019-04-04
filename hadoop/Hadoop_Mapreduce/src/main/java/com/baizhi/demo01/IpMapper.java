package com.baizhi.demo01;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class IpMapper extends Mapper<LongWritable,Text,Text, IntWritable> {
    /**
     *192.168.0.12 1 001 click 5000 2019-01-04 14:44:00
     * @param key :输入文本行字节偏移量
     * @param value:输入文本行
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] tokens = value.toString().split(" ");
        String ip=tokens[0];
        context.write(new Text(ip),new IntWritable(1));
    }
}
