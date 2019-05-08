package vip.ifmm.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.request.LoginRequest;
import vip.ifmm.chat.protocol.response.LoginResponse;
import vip.ifmm.chat.server.util.LoginCheck;
import vip.ifmm.chat.server.util.Session;
import vip.ifmm.chat.server.util.SessionCheck;

import java.util.Date;
import java.util.UUID;

/**
 * 登录请求的处理器
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {

    /**
     * 登录验证流程
     * @param loginRequest 登录请求包
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequest loginRequest) throws Exception {
        System.out.println(String.format("收到%s的登录请求， 验证ing...", loginRequest.getUsername()));

        LoginResponse loginResponse = new LoginResponse();

        if (loginValid(loginRequest)){
            //标识Sessin
            String userId = UUID.randomUUID().toString().split("-")[0];
            SessionCheck.markLogin(new Session(userId, loginRequest.getUsername()), channelHandlerContext.channel());
            //填充响应信息
            loginResponse.setSuccess(true);
            loginResponse.setReason(String.format("%s 您已成功登录！", loginRequest.getUsername()));
            loginResponse.setUserId(userId);
            System.out.println(new Date() + String.format(": %s登录成功", loginRequest.getUsername()));
        }else {
            loginResponse.setSuccess(false);
            loginResponse.setReason(String.format("%s 请检查您的账号密码！", loginRequest.getUsername()));
            System.out.println(new Date() + String.format(": %s登录失败", loginRequest.getUsername()));
        }

        channelHandlerContext.channel().writeAndFlush(loginResponse);
    }

    /**
     * 登录用户验证的逻辑实现方法
     * @param loginRequest 登录请求包
     * @return
     */
    private boolean loginValid(LoginRequest loginRequest){
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionCheck.withdrawLogin(ctx.channel());
    }
}
