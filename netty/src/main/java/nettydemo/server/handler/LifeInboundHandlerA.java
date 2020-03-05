package nettydemo.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class LifeInboundHandlerA extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ctx.channel().id().toString() +" LifeInboundHandlerA 有数据可读：channelRead(): " + byteBuf.toString(Charset.forName("utf-8")));

        byteBuf.writeBytes("A传过来的=======》".getBytes());

        ctx.channel().writeAndFlush("服务端返回一句话");
        super.channelRead(ctx, byteBuf);
    }



//    @Override
//    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("channel 取消线程(NioEventLoop) 的绑定: channelUnregistered()");
//        super.channelUnregistered(ctx);
//    }


}
