package com.baizhi.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.commons.lang.SerializationUtils;

import java.util.List;

/**
 * Created by wdwhwn on 2018/11/16.
 */
public class ByteBufToObjectEncode extends MessageToMessageDecoder{
    /**
     *
     * @param channelHandlerContext 上下文
     * @param o  bytebuf
     * @param list  输出列表
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
        ByteBuf buffer = (ByteBuf) o;
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);
        Object deserialize = SerializationUtils.deserialize(bytes);
        list.add(deserialize);
    }
}
