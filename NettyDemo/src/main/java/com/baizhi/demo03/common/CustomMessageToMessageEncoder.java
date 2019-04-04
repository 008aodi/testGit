package com.baizhi.demo03.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.List;

public class CustomMessageToMessageEncoder extends MessageToMessageEncoder<Object> {
    /**
     *
     * @param ctx
     * @param o 需要编码的对象
     * @param out 将编码的对象放置到输出的数据帧中即可
     * @throws Exception
     */
    protected void encode(ChannelHandlerContext ctx, Object o, List<Object> out) throws Exception {
        ByteBuf buf=ctx.alloc().buffer();
        byte[] bytes = SerializationUtils.serialize((Serializable) o);

        buf.writeBytes(bytes);

        out.add(buf);
    }
}
