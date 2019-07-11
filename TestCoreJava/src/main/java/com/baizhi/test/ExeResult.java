package com.baizhi.test;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.Future;

/**
 * 程序计时器
 * @param <T>
 */
public class ExeResult<T> {
    private static Logger logger = LoggerFactory.getLogger(ExeResult.class);
    private static final long serialVersionUID = 2992987576262574064L;
    private boolean async = false;
    private boolean success = true;
    private T result;
    private Exception exception;
    private long startTime = 0L;
    private long endTime = 0L;
    private long useTime = -1L;
    //无参构造
    public ExeResult(){}
    //get/set方法

    public T getResult() {
        return this.result;
    }

    public ExeResult setResult(T result) {
       return this.putResult(result);
    }

    public Exception getException() {
        return this.exception;
    }

    public ExeResult setException(Exception exception) {
        this.exception = exception;
        this.success = false;
        this.end();
        return this;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public ExeResult setStartTime(long startTime) {
        this.startTime = startTime;
        return this;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public ExeResult setEndTime(long endTime) {
        this.endTime = endTime;
        this.setUseTime(this.endTime-this.startTime);
        return this;

    }

    public long getUseTime() {
        logger.info("{}-获取用时",useTime);
        return useTime;
    }

    public ExeResult setUseTime(long useTime) {
        this.useTime = useTime;
        return this;
    }
    public  static ExeResult start(){
        logger.info("{}-开始计时",System.currentTimeMillis());
        ExeResult result = new ExeResult();
        return result.setStartTime(System.currentTimeMillis());
    }
    public ExeResult end(){
        if (this.endTime<=0L){
            this.setEndTime(System.currentTimeMillis());
        }
        logger.info("{}-结束计时",System.currentTimeMillis());
        return this;
    }
    public ExeResult putResult(T result){
        this.result = result;
        if(result instanceof Future){
            this.async = true;
        }else{
            this.end();
        }
        return this;
    }
    public T resultIfSuccess(T defaultValue){
        return !this.success?defaultValue:this.result;
    }
    public boolean idSuccess(){
        return this.success;
    }
    public ExeResult setSuccess(boolean success){
        this.success = success;
        return this;
    }
    public boolean isAsync(){
        return this.async;
    }
    public ExeResult setAsync(boolean async){
        this.async = async;
        return this;
    }
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
