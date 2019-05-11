package vip.ifmm.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.response.GroupResponse;

/**
 *
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/9 </p>
 */
public class GroupResponseHandler extends SimpleChannelInboundHandler<GroupResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupResponse groupResponse) throws Exception {
        System.out.print("群创建成功，id 为[" + groupResponse.getGroupId() + "], ");
        System.out.println("群里面有：" + groupResponse.getUsernameList());
    }
}
