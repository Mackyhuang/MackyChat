package vip.ifmm.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.response.ShareMessageResponse;
import vip.ifmm.chat.server.util.Session;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/11 </p>
 */
public class ShareMessageResponseHandler extends SimpleChannelInboundHandler<ShareMessageResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ShareMessageResponse shareMessageResponse) throws Exception {
        String sourceGroupId = shareMessageResponse.getSourceGroupId();
        Session sourceUser = shareMessageResponse.getSourceUser();
        System.out.println("收到群[" + sourceGroupId + "]中[" + sourceUser + "]发来的消息：" + shareMessageResponse.getMessage());
    }
}
