package com.baizhi.util;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import sun.misc.Request;

/**
 * Created by wdwhwn on 2018/11/16.
 */
public class MyClientHandle extends ChannelHandlerAdapter{
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端发生异常了");
            cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//            String str="服务端，你好";
//        获取ByteBuf对象
//            ByteBuf buffer = Unpooled.buffer();
//         向ByteBuf中写入数据
//        buffer.writeBytes(str.getBytes());
//        输出数据
//            ctx.writeAndFlush(buffer);
           ctx.writeAndFlush(new RequestData(HelloService.class,"test",new Object[]{"wd"},new Class[]{String.class}));
//        ctx.writeAndFlush("服务器你好");
    }



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//      获取ByteBuf对象  
//        ByteBuf msg1 = (ByteBuf) msg;
//        System.out.println(msg1.toString(CharsetUtil.UTF_8));
//            ctx.close();
        ResponseData rd= (ResponseData) msg;
        System.out.println(rd.getResult());
            ctx.close();
    }
}
