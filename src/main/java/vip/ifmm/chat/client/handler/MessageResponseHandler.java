package vip.ifmm.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.response.MessageResponse;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponse messageResponse) throws Exception {
        String sourceUserId = messageResponse.getSourceUserId();
        String sourceUsername = messageResponse.getSourceUsername();
        String message = messageResponse.getMessage();
        System.out.println(String.format("[%s] - [%s] : \n %s", sourceUserId, sourceUsername, message));
    }
}
