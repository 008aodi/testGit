package com.baizhi.demo01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CustomJobSubmiter extends Configured implements Tool {
    public int run(String[] strings) throws Exception {
       //1封装Job对象
        Configuration conf = getConf();
        Job job = Job.getInstance();

        //设置jar包类加载器
        job.setJarByClass(CustomJobSubmiter.class);

        //2.设置数据读入写出的格式
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        //3设置处理数据路径
        Path src = new Path("/demo/access");
        TextInputFormat.addInputPath(job,src);
        Path dst = new Path("/demo/result");
        TextOutputFormat.setOutputPath(job,dst);
        //4设置数据计算逻辑
        job.setMapperClass(IpMapper.class);
        job.setReducerClass(IpReducer.class);
        //5设置Mapper和Reducer输出泛型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //6提交任务
       // job.submit();
        job.waitForCompletion(true);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new CustomJobSubmiter(),args);
    }
}
