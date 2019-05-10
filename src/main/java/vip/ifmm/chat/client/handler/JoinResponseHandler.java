package vip.ifmm.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.response.JoinResponse;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/10 </p>
 */
public class JoinResponseHandler extends SimpleChannelInboundHandler<JoinResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinResponse joinResponse) throws Exception {
        if (joinResponse.isSuccess()) {
            System.out.println("加入群[" + joinResponse.getGroupId() + "]成功!");
        } else {
            System.err.println("加入群[" + joinResponse.getGroupId() + "]失败，原因为：" + joinResponse.getReason());
        }
    }
}
