package vip.ifmm.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import vip.ifmm.chat.protocol.request.ListRequest;
import vip.ifmm.chat.protocol.response.ListResponse;
import vip.ifmm.chat.server.util.Session;
import vip.ifmm.chat.server.util.SessionCheck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/10 </p>
 */
@ChannelHandler.Sharable
public class ListRequestHandler extends SimpleChannelInboundHandler<ListRequest> {

    public static final ListRequestHandler INSTANCE = new ListRequestHandler();

    private ListRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListRequest listRequest) throws Exception {
        String groupId = listRequest.getGroupId();
        ChannelGroup channelGroup = SessionCheck.getChannelGroup(groupId);

        Iterator<Channel> iterator = channelGroup.iterator();
        List<Session> sessionList = new ArrayList<>();
        //遍历channelGroup 获取每个Channel的session信息
        while (iterator.hasNext()){
            Session session = SessionCheck.getSession(iterator.next());
            sessionList.add(session);
        }

        ListResponse listResponse = new ListResponse();
        listResponse.setGroupId(groupId);
        listResponse.setSessionList(sessionList);

        channelHandlerContext.channel().writeAndFlush(listResponse);
    }
}
