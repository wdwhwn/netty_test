package com.baizhi.test;

import com.baizhi.util.ByteBufToObjectEncode;
import com.baizhi.util.MyServerHandle;
import com.baizhi.util.ObjectToByteBufEncode;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 *
 * Created by wdwhwn on 2018/11/15.
 */
public class NettyServerTest {

    public static void main(String[] args) throws InterruptedException {
//        1 初始化引导对象
        ServerBootstrap bootstrap = new ServerBootstrap();
//        2 请求转发和io处理线程池
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        NioEventLoopGroup nioEventLoopGroup1 = new NioEventLoopGroup();
//        3 设置通道服务
        bootstrap.channel(NioServerSocketChannel.class);
//        4 绑定线程池
        bootstrap.group(nioEventLoopGroup,nioEventLoopGroup1);
//        5 初始化通讯管道
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            /**
             * 组装通道管道和通道处理器
             * @param socketChannel
             * @throws Exception
             */
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                pipeline.addLast(new LengthFieldPrepender(2));
                pipeline.addLast(new ObjectToByteBufEncode());
                pipeline.addLast(new ByteBufToObjectEncode());
                pipeline.addLast(new MyServerHandle());

            }
        });
        System.out.println("服务器已经启动了");
//        6 绑定端口，并开启服务
        ChannelFuture sync = bootstrap.bind("127.0.0.1", 8881).sync();
//        7 关闭通道
            sync.channel().closeFuture().sync();
//        8 释放资源
        nioEventLoopGroup.shutdownGracefully();
        nioEventLoopGroup1.shutdownGracefully();
    }
}
