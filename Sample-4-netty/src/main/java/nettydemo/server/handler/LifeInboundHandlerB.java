package nettydemo.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class LifeInboundHandlerB extends ChannelInboundHandlerAdapter {
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(new Date() + " LifeInboundHandlerB ：handlerAdded() ");
//        super.handlerAdded(ctx);
//    }
//
//    @Override
//    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("LifeInboundHandlerB 绑定到线程(NioEventLoop)：channelRegistered()");
//        super.channelRegistered(ctx);
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("LifeInboundHandlerB 准备就绪：channelActive()");
//        super.channelActive(ctx);
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ctx.channel().id().toString() +" LifeInboundHandleB 有数据可读：channelRead(): " + byteBuf.toString(Charset.forName("utf-8")));
        super.channelActive(ctx);
    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("LifeInboundHandlerB 某次数据读完：channelReadComplete()");
//        super.channelReadComplete(ctx);
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("LifeInboundHandlerB 被关闭：channelInactive()");
//        super.channelInactive(ctx);
//    }

//    @Override
//    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("channel 取消线程(NioEventLoop) 的绑定: channelUnregistered()");
//        super.channelUnregistered(ctx);
//    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LifeInboundHandlerB 逻辑处理器被移除：handlerRemoved()");
        super.handlerRemoved(ctx);
    }
}
