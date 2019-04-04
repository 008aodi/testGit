package com.baizhi.demo01;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class IpReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    /**
     *
     * @param key :ip
     * @param values: Int[]{1,1,1,..}
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int total=0;
        for (IntWritable value : values) {
            total+=value.get();
        }
        context.write(key,new IntWritable(total));
    }
}
