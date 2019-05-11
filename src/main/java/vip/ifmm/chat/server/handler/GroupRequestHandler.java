package vip.ifmm.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import vip.ifmm.chat.protocol.request.GroupRequest;
import vip.ifmm.chat.protocol.response.GroupResponse;
import vip.ifmm.chat.server.util.SessionCheck;
import vip.ifmm.chat.util.SeqUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/9 </p>
 */
@ChannelHandler.Sharable
public class GroupRequestHandler extends SimpleChannelInboundHandler<GroupRequest> {

    public static final GroupRequestHandler INSTANCE = new GroupRequestHandler();

    private GroupRequestHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupRequest groupRequest) throws Exception {
        //获取群组建立请求包里面的用户id队列
        List<String> userIdList = groupRequest.getUserIdList();

        //channel分组
        ChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());
        //预装用户id对应的用户名
        List<String> usernameList = new ArrayList<>();

        Iterator<String> iterator = userIdList.iterator();
        while (iterator.hasNext()) {
            Channel channel = SessionCheck.getChannel(iterator.next());
            if (channel != null) {
                channelGroup.add(channel);
                usernameList.add(SessionCheck.getSession(channel).getUsername());
            }
        }

        //群组构建响应包
        GroupResponse groupResponse = new GroupResponse();
        String groupId = SeqUtil.getSeq();
        groupResponse.setGroupId(groupId);
        groupResponse.setSuccess(true);
        groupResponse.setUsernameList(usernameList);

        channelGroup.writeAndFlush(groupResponse);

        System.out.print("群创建成功，id 为[" + groupResponse.getGroupId() + "], ");
        System.out.println("群里面有：" + groupResponse.getUsernameList());

        //将这个群组存入系统群组map
        SessionCheck.markGroup(groupId, channelGroup);
    }
}
