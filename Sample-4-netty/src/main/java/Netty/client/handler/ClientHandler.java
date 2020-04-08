package Netty.client.handler;

import Netty.common.Packet;
import Netty.common.PacketCodeC;
import Netty.domain.request.LoginRequestPacket;
import Netty.domain.response.LoginResponsePacket;
import Netty.domain.response.MessageResponsePacket;
import Netty.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

public class ClientHandler extends ChannelInboundHandlerAdapter {

      @Override
      public void channelActive(ChannelHandlerContext ctx) {
          System.out.println(new Date() + ": 客户端开始登录");

          //创建登录对象
          LoginRequestPacket loginRequestPacket = new LoginRequestPacket()
                  .setUserId(UUID.randomUUID().toString())
                  .setUsername("lkyi")
                  .setPassword("lkyi");

          //编码
//          ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

          //写数据
//          ctx.channel().writeAndFlush(buffer);
      }

      @Override
      public void channelRead(ChannelHandlerContext ctx, Object msg) {
          ByteBuf byteBuf = (ByteBuf) msg;

          Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

          if(packet instanceof LoginResponsePacket) {
              LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

              if(loginResponsePacket.isSuccess()) {
                  System.out.println(new Date() + ": 客户端登录成功");
                  LoginUtil.markAsLogin(ctx.channel());
              } else {
                  System.out.println(new Date() + ": 客户端登录失败，原因："+ loginResponsePacket.getReason());
              }
          } else if(packet instanceof MessageResponsePacket) {
              MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
              System.out.println(new Date() + ": 收到服务端的消息：" + messageResponsePacket.getMessage());
          }
      }

}
