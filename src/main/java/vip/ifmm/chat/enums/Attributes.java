package vip.ifmm.chat.enums;

import io.netty.util.AttributeKey;
import vip.ifmm.chat.server.util.Session;

/**
 * 用于给channel添加attr标识
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public interface Attributes {
    //登录标识
    AttributeKey<Boolean> LOGIN_FLAG = AttributeKey.newInstance("login");
    //会话标识
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
