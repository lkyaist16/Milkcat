package nettydemo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import nettydemo.server.handler.LifeInboundHandlerA;
import nettydemo.server.handler.LifeInboundHandlerB;

public class Server {

    public static void main(String[] args) {

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //处理连接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        //selector
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            serverBootstrap
                    //设定使用的线程组
                    .group(bossGroup, workerGroup)
                    //设定channel类型
                    .channel(NioServerSocketChannel.class)
                    //BIO模型
//                .channel(OioServerSocketChannel.class)
                    //设置一些底层属性
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //设置连接类型
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //管道
                            ch.pipeline().addLast(new LifeInboundHandlerA());
                            ch.pipeline().addLast(new LifeInboundHandlerB());
                        }
                    });

            // ChannelFuture 保存Channel异步操作的结果 sync() 设置为同步操作
            ChannelFuture future = serverBootstrap
                    .bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
                        @Override
                        public void operationComplete(Future<? super Void> future) throws Exception {
                            if (future.isSuccess()) {
                                System.out.println("端口绑定成功！");
                            } else {
                                System.err.println("端口绑定失败！");
                            }
                        }
                    });
//            if(future.isSuccess()) {
//                System.out.println("端口绑定成功！");
//            }

//           serverBootstrap.bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
//                        @Override
//                        public void operationComplete(Future<? super Void> future) throws Exception {
//                            if (future.isSuccess()) {
//                                System.out.println("端口绑定成功！");
//                            } else {
//                                System.err.println("端口绑定失败！");
//                            }
//                        }
//                    });

            System.out.println("程序运行异步测试！！！！");

            future.channel().closeFuture().sync();


        } catch (Exception e) {
            System.err.println("服务器启动失败！");
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}
