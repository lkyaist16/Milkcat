package Netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx){


        System.out.println(new Date() + ": 客户端写出数据");

        ByteBuf buffer = getByteBuf(ctx);


        ctx.channel().writeAndFlush(buffer);

    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

    }


    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {

        //获取二进制对象
        ByteBuf buffer = ctx.alloc().buffer();

        //准备接收
        byte[] bytes = "你好，钢铁侠".getBytes(Charset.forName("utf-8"));

        //写入
        buffer.writeBytes(bytes);

        return buffer;
    }

}
