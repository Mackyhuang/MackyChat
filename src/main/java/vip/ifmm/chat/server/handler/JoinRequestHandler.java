package vip.ifmm.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import vip.ifmm.chat.protocol.request.JoinRequest;
import vip.ifmm.chat.protocol.response.JoinResponse;
import vip.ifmm.chat.server.util.SessionCheck;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/10 </p>
 */
@ChannelHandler.Sharable
public class JoinRequestHandler extends SimpleChannelInboundHandler<JoinRequest> {

    public static final JoinRequestHandler INSTANCE = new JoinRequestHandler();

    private JoinRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinRequest joinRequest) throws Exception {
        //获取groupId以及它对应的 channelGroup
        String groupId = joinRequest.getGroupId();
        ChannelGroup channelGroup = SessionCheck.getChannelGroup(groupId);
        //将当前信道加入群组
        channelGroup.add(channelHandlerContext.channel());
        System.out.println(String.format("%s 成功加入群聊 %s", SessionCheck.getSession(channelHandlerContext.channel()).getUsername(), groupId));
        JoinResponse joinResponse = new JoinResponse();
        //写回响应
        joinResponse.setGroupId(groupId);
        joinResponse.setSuccess(true);
        channelHandlerContext.channel().writeAndFlush(joinResponse);
    }
}
