package vip.ifmm.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import vip.ifmm.chat.protocol.request.ShareMessageRequest;
import vip.ifmm.chat.protocol.response.ShareMessageResponse;
import vip.ifmm.chat.server.util.SessionCheck;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/11 </p>
 */
@ChannelHandler.Sharable
public class ShareMessageRequestHandler extends SimpleChannelInboundHandler<ShareMessageRequest> {

    public static final ShareMessageRequestHandler INSTANCE = new ShareMessageRequestHandler();

    private ShareMessageRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ShareMessageRequest shareMessageRequest) throws Exception {
        //获取groupId相应的群组
        String destGroupId = shareMessageRequest.getDestGroupId();
        ChannelGroup channelGroup = SessionCheck.getChannelGroup(destGroupId);
        //将这条信息封装，准备传送给群组的每个用户
        ShareMessageResponse shareMessageResponse = new ShareMessageResponse();
        shareMessageResponse.setSourceGroupId(shareMessageRequest.getDestGroupId());
        shareMessageResponse.setMessage(shareMessageRequest.getMessage());
        shareMessageResponse.setSourceUser(SessionCheck.getSession(channelHandlerContext.channel()));
        //传送给群组的每个用户
        channelGroup.writeAndFlush(shareMessageResponse);
    }
}
