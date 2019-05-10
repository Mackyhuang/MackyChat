package vip.ifmm.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.response.LogoutResponse;
import vip.ifmm.chat.server.util.SessionCheck;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/9 </p>
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutResponse logoutResponse) throws Exception {
        SessionCheck.withdrawLogin(channelHandlerContext.channel());
        System.out.println("用户注销成功！");
    }
}
