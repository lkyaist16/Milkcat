package nettydemo.client;

import Netty.client.handler.FirstClientHandler;
import Netty.server.handler.outbound.OutBoundHandlerA;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

    public static void main(String[] args) {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap()
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                       ch.pipeline().addLast(new FirstClientHandler());
                       ch.pipeline().addLast(new OutBoundHandlerA());
//                       ch.pipeline().addLast(new OutBoundHandlerB());
                    }
                });

        bootstrap.connect("127.0.0.1",8000).addListener(future -> {
            if(future.isSuccess()) {
                System.out.println("连接成功！");
            } else {
                System.out.println("连接失败！");
            }
        });
    }

}
