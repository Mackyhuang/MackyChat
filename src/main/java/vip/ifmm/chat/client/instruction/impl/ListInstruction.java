package vip.ifmm.chat.client.instruction.impl;

import io.netty.channel.Channel;
import vip.ifmm.chat.client.instruction.CommonInstruction;
import vip.ifmm.chat.protocol.request.ListRequest;

import java.util.Scanner;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/10 </p>
 */
public class ListInstruction implements CommonInstruction {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入 groupId 以获取群组成员：");
        String groupId = scanner.nextLine();

        ListRequest listRequest = new ListRequest();
        listRequest.setGroupId(groupId);

        channel.writeAndFlush(listRequest);
    }
}
