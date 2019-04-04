package com.baizhi.demo02.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.commons.lang3.SerializationUtils;

import java.util.List;

public class CustomMessageToMessageDecoder extends MessageToMessageDecoder<ByteBuf> {
    /**
     *
     * @param ctx
     * @param byteBuf :需要解码的数据
     * @param out    :需要将就解码的对象放置到数据帧中
     * @throws Exception
     */
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        System.out.println("解码...");
        byte[] bytes=new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);//将bytebuf中的数据读入到bytes数值中
        //解码数据
        Object o= SerializationUtils.deserialize(bytes);
        //将解码的数据放置到数据帧中
        out.add(o);

    }
}
