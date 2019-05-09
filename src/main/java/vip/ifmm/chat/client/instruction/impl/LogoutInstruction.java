package vip.ifmm.chat.client.instruction.impl;

import io.netty.channel.Channel;
import vip.ifmm.chat.client.instruction.CommonInstruction;
import vip.ifmm.chat.protocol.request.LogoutRequest;

import java.util.Scanner;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/9 </p>
 */
public class LogoutInstruction implements CommonInstruction {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequest logoutRequest = new LogoutRequest();
        channel.writeAndFlush(logoutRequest);
    }
}
