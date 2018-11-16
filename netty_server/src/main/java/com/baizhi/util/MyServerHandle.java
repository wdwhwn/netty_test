package com.baizhi.util;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wdwhwn on 2018/11/15.
 */
public class MyServerHandle extends ChannelHandlerAdapter{
    /**
     * 异常事件回调方法
     * @param ctx 上下文
     * @param cause 异常
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务器端发生异常了");
//              打印站追踪信息
            cause.printStackTrace();
//            关闭上下文   出现异常就可以关闭了
            ctx.close();
    }

    /**
     *发生读事件时回调的方法
     * 发生写事件时回调的方法
     * @param ctx 上下文
     * @param msg 数据
     * @throws Exception
     */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        读取客户端请求
//        ByteBuf byteBuf= (ByteBuf) msg;
//        System.out.println("客户端发送的请求是："+byteBuf.toString(CharsetUtil.UTF_8));
//        System.out.println(msg);
//        清空byteBuf
//        byteBuf.clear();
//        在buteBuf中存储数据
//        byteBuf.writeBytes("客户端你好".getBytes());
//       将byteBuf转换为字节  发生写事件
//        ctx.writeAndFlush(byteBuf);
       /* ChannelFuture channelFuture=null;
        for(int i=0;i<100;i++){
             channelFuture = ctx.writeAndFlush(i + "||" + new Date());

        }*/
//        System.out.println("1234679");
        ChannelFuture channelFuture =null;
        try {
            RequestData rq = (RequestData) msg;
            Class className = rq.getClassName();
            Method declaredMethod = className.getDeclaredMethod(rq.getMethodName(), rq.getTypeList());
            Object invoke = declaredMethod.invoke(new HelloServiceImpl(), rq.getParamList());
            channelFuture= ctx.writeAndFlush(new ResponseData(invoke));
        }catch (InvocationTargetException e) {
            e.printStackTrace();
            channelFuture=ctx.writeAndFlush(new ResponseData(e));
        }
//        ChannelFuture channelFuture = ctx.writeAndFlush("给客户端的响应");
//        添加监听器  成功时关闭通道
//        channelFuture.addListener(ChannelFutureListener.CLOSE);
//        不成功时，抛出异常
//        channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
//        不成功时，关闭通道
//        channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);

    }
}
