package vip.ifmm.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import vip.ifmm.chat.protocol.request.QuitRequest;
import vip.ifmm.chat.protocol.response.QuitResponse;
import vip.ifmm.chat.server.util.SessionCheck;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/10 </p>
 */
@ChannelHandler.Sharable
public class QuitRequestHandler extends SimpleChannelInboundHandler<QuitRequest> {

    public static final QuitRequestHandler INSTANCE = new QuitRequestHandler();

    private QuitRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitRequest quitRequest) throws Exception {
        //获取群聊对应的channelGroup并且相处当前channel
        String groupId = quitRequest.getGroupId();
        ChannelGroup channelGroup = SessionCheck.getChannelGroup(groupId);
        channelGroup.remove(channelHandlerContext.channel());
        System.out.println(String.format("%s 成功退出群聊 %s", SessionCheck.getSession(channelHandlerContext.channel()).getUsername(), groupId));
        QuitResponse quitResponse = new QuitResponse();
        quitResponse.setGroupId(groupId);
        quitResponse.setSuccess(true);

        channelHandlerContext.channel().writeAndFlush(quitResponse);
    }
}
