package vip.ifmm.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.request.LogoutRequest;
import vip.ifmm.chat.protocol.response.LogoutResponse;
import vip.ifmm.chat.server.util.SessionCheck;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/9 </p>
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequest> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequest logoutRequest) throws Exception {
        String username = SessionCheck.getSession(channelHandlerContext.channel()).getUsername();
        SessionCheck.withdrawLogin(channelHandlerContext.channel());
        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setSuccess(true);

        channelHandlerContext.channel().writeAndFlush(logoutResponse);
        System.out.println(String.format("用户 [%s] 已经注销！", username));
    }
}
