package vip.ifmm.chat.enums;

import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Boolean> LOGIN_FLAG = AttributeKey.newInstance("login");
}
