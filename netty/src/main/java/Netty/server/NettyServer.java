package Netty.server;

import Netty.common.PacketDecoder;
import Netty.common.PacketEncoder;
import Netty.common.Spliter;
import Netty.server.handler.AuthHandler;
import Netty.server.handler.LoginRequestHandler;
import Netty.server.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {
    public static void main(String[] args) {

        //引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //监听端口，看有没有新连接的线程组
        NioEventLoopGroup boss = new NioEventLoopGroup();

        //看有没有读写
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap
                .group(boss, worker)
                //指定io模型
                .channel(NioServerSocketChannel.class)
                //初始化服务端可连接队列
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
//                        ch.pipeline().addLast(new LifeCycleTestHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                    //异步方法
                }).bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口绑定成功！！！！！！！");
                } else {
                    System.out.println("端口绑定失败！！！！！！");
                }
            }
        });
    }
}
