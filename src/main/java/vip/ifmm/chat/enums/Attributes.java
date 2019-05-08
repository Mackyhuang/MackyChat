package vip.ifmm.chat.enums;

import io.netty.util.AttributeKey;

/**
 * 用于给channel添加attr标识
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public interface Attributes {
    //登录标识
    AttributeKey<Boolean> LOGIN_FLAG = AttributeKey.newInstance("login");
}
