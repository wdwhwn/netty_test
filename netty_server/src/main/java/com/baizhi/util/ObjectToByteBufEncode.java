package com.baizhi.util;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wdwhwn on 2018/11/16.
 */
public class ObjectToByteBufEncode extends MessageToMessageEncoder {
    /**
     * 编码器
     * @param channelHandlerContext  上下文
     * @param o   对象类型
     * @param list 输出列表
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
        byte[] serialize = SerializationUtils.serialize((Serializable)o);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(serialize);
        list.add(buffer);

    }
}
