package Netty.server.handler;

import Netty.common.Packet;
import Netty.common.PacketCodeC;
import Netty.domain.request.LoginRequestPacket;
import Netty.domain.request.MessageRequestPacket;
import Netty.domain.response.LoginResponsePacket;
import Netty.domain.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket) {
            System.out.println(new Date() + "收到客户端登录请求。。。。");

            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

            loginResponsePacket.setVersion(packet.getVersion());

            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功！");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败！");
            }

            // 编码
//            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
//            ctx.channel().writeAndFlush(responseByteBuf);
        } else if(packet instanceof MessageRequestPacket) {
            // 客户端发来的消息
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            System.out.println(new Date() +": 收到客户端消息： " + messageRequestPacket.getMessage());

            messageResponsePacket.setMessage("服务端恢复【 " + messageResponsePacket.getMessage() + "】");

//            ByteBuf responseBytebuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);

//            ctx.channel().writeAndFlush(responseBytebuf);

        }

    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
