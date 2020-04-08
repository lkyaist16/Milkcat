package nettydemo.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    //连接建立成功以后创建
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String msg = "客户端已经创建成功！";
        System.out.println(new Date() + " 连接创建成功后向服务端发送数据：" + msg);

        ByteBuf byteBuf = ctx.alloc().buffer();

        byte[] bytes = msg.getBytes(Charset.forName("utf-8"));

        byteBuf.writeBytes(bytes);

        ctx.channel().writeAndFlush(byteBuf);

    }
}
