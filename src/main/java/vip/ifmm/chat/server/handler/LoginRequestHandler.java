package vip.ifmm.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.request.LoginRequest;
import vip.ifmm.chat.protocol.response.LoginResponse;
import vip.ifmm.chat.server.util.LoginCheck;

import java.util.Date;

/**
 * 登录请求的处理器
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequest loginRequest) throws Exception {
        System.out.println(String.format("收到%s的登录请求， 验证ing...", loginRequest.getUsername()));

        LoginResponse loginResponse = new LoginResponse();

        if (loginValid(loginRequest)){
            loginResponse.setSuccess(true);
            LoginCheck.markLogin(channelHandlerContext.channel());
            loginResponse.setReason(String.format("%s 您已成功登录！", loginRequest.getUsername()));
            System.out.println(new Date() + String.format("%d登录成功", loginRequest.getUsername()));
        }else {
            loginResponse.setSuccess(false);
            loginResponse.setReason(String.format("%s 请检查您的账号密码！", loginRequest.getUsername()));
            System.out.println(new Date() + String.format("%d登录失败", loginRequest.getUsername()));
        }

        channelHandlerContext.writeAndFlush(loginResponse);
    }

    /**
     * 登录用户验证的逻辑实现方法
     * @param loginRequest
     * @return
     */
    private boolean loginValid(LoginRequest loginRequest){
        return true;
    }
}
