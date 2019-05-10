package vip.ifmm.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.response.ListResponse;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/10 </p>
 */
public class ListResponseHandler extends SimpleChannelInboundHandler<ListResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListResponse listResponse) throws Exception {
        System.out.println("群[" + listResponse.getGroupId() + "]中的人包括：" + listResponse.getSessionList());
    }
}
