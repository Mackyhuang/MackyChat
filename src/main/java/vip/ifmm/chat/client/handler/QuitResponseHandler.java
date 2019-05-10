package vip.ifmm.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.response.QuitResponse;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/10 </p>
 */
public class QuitResponseHandler extends SimpleChannelInboundHandler<QuitResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitResponse quitResponse) throws Exception {
        if (quitResponse.isSuccess()) {
            System.out.println("退出群聊[" + quitResponse.getGroupId() + "]成功！");
        } else {
            System.out.println("退出群聊[" + quitResponse.getGroupId() + "]失败！");
        }
    }
}
