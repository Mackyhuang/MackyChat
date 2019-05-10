package vip.ifmm.chat.client.instruction.impl;

import io.netty.channel.Channel;
import vip.ifmm.chat.client.instruction.CommonInstruction;
import vip.ifmm.chat.protocol.request.JoinRequest;
import vip.ifmm.chat.protocol.request.QuitRequest;

import java.util.Scanner;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/10 </p>
 */
public class QuitInstruction implements CommonInstruction {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入您要退出的群聊ID：");
        String groupId = scanner.nextLine();

        QuitRequest quitRequest = new QuitRequest();
        quitRequest.setGroupId(groupId);

        channel.writeAndFlush(quitRequest);
    }
}
