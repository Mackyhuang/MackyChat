package vip.ifmm.chat.server.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import vip.ifmm.chat.enums.Attributes;

@Deprecated
public class LoginCheck {

    public static void markLogin(Channel channel) {
        channel.attr(Attributes.LOGIN_FLAG).set(true);
    }

    public static boolean checkLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN_FLAG);

        return loginAttr.get() != null;
    }
}
