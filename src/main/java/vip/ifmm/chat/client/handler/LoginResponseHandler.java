package vip.ifmm.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.request.LoginRequest;
import vip.ifmm.chat.protocol.response.LoginResponse;
import vip.ifmm.chat.server.util.LoginCheck;

import java.util.Date;
import java.util.UUID;

/**
 * 登录响应的处理器
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponse> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 创建登录对象
        LoginRequest loginRequestPacket = new LoginRequest();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("macky");
        loginRequestPacket.setPassword("0000");

        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponse loginResponse) throws Exception {
        if (loginResponse.isSuccess()) {
            System.out.println(new Date() + ": 登录成功");
            LoginCheck.markLogin(channelHandlerContext.channel());
        } else {
            System.out.println(new Date() + ": 客户端登录失败：" + loginResponse.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
