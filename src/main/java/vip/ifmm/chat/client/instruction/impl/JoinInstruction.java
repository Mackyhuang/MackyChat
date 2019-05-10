package vip.ifmm.chat.client.instruction.impl;

import io.netty.channel.Channel;
import vip.ifmm.chat.client.instruction.CommonInstruction;
import vip.ifmm.chat.protocol.request.JoinRequest;

import java.util.Scanner;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/10 </p>
 */
public class JoinInstruction implements CommonInstruction {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入您要加入的群聊ID：");
        String groupId = scanner.nextLine();

        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setGroupId(groupId);

        channel.writeAndFlush(joinRequest);
    }
}
