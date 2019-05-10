package vip.ifmm.chat.server.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import vip.ifmm.chat.enums.Attributes;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/8 </p>
 */
public class SessionCheck {

    //用户标识和信道的关系映射
    public static final Map<String, Channel> userChannelMap = new ConcurrentHashMap<>();
    //群组的id与信道组的映射关系
    public static final Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

    /**
     * 登录成功修改标识
     * @param session
     * @param channel
     */
    public static void markLogin(Session session, Channel channel){
        channel.attr(Attributes.SESSION).set(session);
        userChannelMap.put(session.getUserId(), channel);
    }

    /**
     * 用户标识注销
     */
    public static void withdrawLogin(Channel channel){
        if (checkLogin(channel)){
            userChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    /**
     * 验证是否登录
     */
    public static boolean checkLogin(Channel channel){
        return channel.hasAttr(Attributes.SESSION);
    }

    /**
     * 获取Session
     */
    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }

    /**
     * 获取信道
     * @param userId
     * @return
     */
    public static Channel getChannel(String userId){
        return userChannelMap.get(userId);
    }

    /**
     * 将群组存进映射器
     * @param groupId
     * @param channelGroup
     */
    public static void markGroup(String groupId, ChannelGroup channelGroup){
        channelGroupMap.put(groupId, channelGroup);
    }

    /**
     * 从群组map中获取 groupId对应的那个
     */
    public static ChannelGroup getChannelGroup(String groupId){
        return channelGroupMap.get(groupId);
    }
}
