package Netty.client;

import Netty.client.handler.LoginResponseHandler;
import Netty.client.handler.MessageResponseHandler;
import Netty.common.PacketDecoder;
import Netty.common.PacketEncoder;
import Netty.common.Spliter;
import Netty.domain.request.LoginRequestPacket;
import Netty.domain.request.MessageRequestPacket;
import Netty.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    private static Integer MAX_RETRY = 3;

    private static String HOST = "127.0.0.1";

    private static Integer PORT = 8000;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        //指定线程模型
        bootstrap.group(group)
                //指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                // IO 处理逻辑
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new Spliter());
                        channel.pipeline().addLast(new PacketDecoder());
                        channel.pipeline().addLast(new LoginResponseHandler());
                        channel.pipeline().addLast(new MessageResponseHandler());
                        channel.pipeline().addLast(new PacketEncoder());
                    }
                });

          connect(bootstrap, HOST, PORT, MAX_RETRY);

//        while (true) {
//            channel.writeAndFlush(new Date() + ": hello world!");
//
//        }

    }


    private static Channel connect(Bootstrap bootstrap, String host, int port, int retry) {
        return bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功！！！！！！");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.out.println(new Date() + ": 连接超时");
            } else {
                int order = (MAX_RETRY - retry) + 1;

                int delay = 1 << order;

                System.out.println(new Date() + ": 连接失败，第" + order + "次重连……");

                bootstrap.config().group().schedule(() ->
                        connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        }).channel();

    }

    private static void startConsoleThread(Channel channel) {

        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();


        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    System.out.println("输入用户名登录：");
                    String userName = sc.nextLine();
                    loginRequestPacket.setUsername(userName);

                    // 密码使用默认的
                    loginRequestPacket.setPassword("pwd");

                    channel.writeAndFlush(loginRequestPacket);

                    waitForLoginResponse();

                } else {
//                    System.out.println("to userId: ");
                    String toUserId = sc.next();

//                    System.out.println("message: ");
                    String message = sc.next();

                    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
                }
            }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
