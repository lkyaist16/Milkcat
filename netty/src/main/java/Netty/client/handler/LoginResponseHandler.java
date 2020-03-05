package Netty.client.handler;

import Netty.domain.Session;
import Netty.domain.response.LoginResponsePacket;
import Netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("LoginResponseHandler 初始化成功");
//        //创建登录对象
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserId(UUID.randomUUID().toString());
//        loginRequestPacket.setUsername("lkyi");
//        loginRequestPacket.setPassword("lkyi");
//        ctx.channel().writeAndFlush(loginRequestPacket);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) {

        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if (loginResponsePacket.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + loginResponsePacket.getUserId());
//            LoginUtil.markAsLogin(channelHandlerContext.channel());
            SessionUtil.bindSession(new Session(userId, userName), channelHandlerContext.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭！");
    }
}
