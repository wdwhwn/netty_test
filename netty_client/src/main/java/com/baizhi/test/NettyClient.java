package com.baizhi.test;


import com.baizhi.util.ByteBufToObjectEncode;
import com.baizhi.util.MyClientHandle;
import com.baizhi.util.ObjectToByteBufEncode;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * Created by wdwhwn on 2018/11/16.
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
//        1. 初始化引导对象
        Bootstrap bootstrap = new Bootstrap();
//        2. 初始化IO线程池
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
//        3. 绑定线程池
        bootstrap.group(workGroup);
//        4. 初始化通道服务
        bootstrap.channel(NioSocketChannel.class);
//        5. 初始通讯通道
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                pipeline.addLast(new LengthFieldPrepender(2));
                pipeline.addLast(new ObjectToByteBufEncode());
                pipeline.addLast(new ByteBufToObjectEncode());
                pipeline.addLast(new MyClientHandle());
            }
        });
//        6. 连接服务
        ChannelFuture connect = bootstrap.connect("127.0.0.1", 8881);
//        7. 关闭服务
        connect.channel().closeFuture().sync();
//        8. 释放资源
        workGroup.shutdownGracefully();
    }
}
