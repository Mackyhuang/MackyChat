package vip.ifmm.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import vip.ifmm.chat.server.util.LoginCheck;

/**
 * 登录验证处理器
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class VerifyHandler extends ChannelInboundHandlerAdapter {

    /**
     * 验证流程
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //若验证通过，解除这个处理器，消息向后传递 否则关闭通道
        if (LoginCheck.checkLogin(ctx.channel())){
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        } else {
            ctx.channel().close();
        }
    }

    /**
     * 处理器被移除的回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginCheck.checkLogin(ctx.channel())) {
            System.out.println("验证连接成功！");
        } else {
            System.out.println("强制关闭连接！[用户未登录]");
        }
    }
}
