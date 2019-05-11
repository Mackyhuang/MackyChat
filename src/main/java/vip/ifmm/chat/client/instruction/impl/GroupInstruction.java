package vip.ifmm.chat.client.instruction.impl;

import io.netty.channel.Channel;
import vip.ifmm.chat.client.instruction.CommonInstruction;
import vip.ifmm.chat.protocol.request.GroupRequest;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 创建群组的操作
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/9 </p>
 */
public class GroupInstruction implements CommonInstruction {
    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        GroupRequest groupRequest = new GroupRequest();

        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String userIds = scanner.nextLine();
        groupRequest.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));
        channel.writeAndFlush(groupRequest);
    }

}
