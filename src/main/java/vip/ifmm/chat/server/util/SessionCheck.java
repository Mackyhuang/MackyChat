package vip.ifmm.chat.server.util;

import io.netty.channel.Channel;
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
}
