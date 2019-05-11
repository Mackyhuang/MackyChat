package vip.ifmm.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.request.MessageRequest;
import vip.ifmm.chat.protocol.response.MessageResponse;
import vip.ifmm.chat.server.util.Session;
import vip.ifmm.chat.server.util.SessionCheck;

import java.util.Date;

/**
 * 消息请求处理器
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequest> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler() {
    }

    /**
     * 消息处理流程
     * @param messageRequest 消息请求包
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequest messageRequest) throws Exception {
        //当前发送消息端的session信息
        Session session = SessionCheck.getSession(channelHandlerContext.channel());
        //构造数据包
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setSourceUserId(session.getUserId());
        messageResponse.setSourceUsername(session.getUsername());
        messageResponse.setMessage(messageRequest.getMessage());
        //接收端的信道
        Channel destChannel = SessionCheck.getChannel(messageRequest.getDestUserId());

        if (destChannel != null && SessionCheck.checkLogin(destChannel)){
            destChannel.writeAndFlush(messageResponse);
        }else {
            System.err.println("[" + messageRequest.getDestUserId() + "] 不在线，发送失败!");
        }

    }
}
