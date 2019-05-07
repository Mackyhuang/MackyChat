package vip.ifmm.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.request.MessageRequest;
import vip.ifmm.chat.protocol.response.MessageResponse;

import java.util.Date;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequest messageRequest) throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequest.getMessage());
        messageResponse.setMessage("服务端回复[" + messageRequest.getMessage() + "]");

        channelHandlerContext.channel().writeAndFlush(messageResponse);
    }
}
