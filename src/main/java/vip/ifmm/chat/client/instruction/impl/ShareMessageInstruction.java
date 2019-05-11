package vip.ifmm.chat.client.instruction.impl;

import io.netty.channel.Channel;
import vip.ifmm.chat.client.instruction.CommonInstruction;
import vip.ifmm.chat.protocol.request.ShareMessageRequest;

import java.util.Scanner;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/11 </p>
 */
public class ShareMessageInstruction implements CommonInstruction {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入需要发送的群组的groupId :");

        String groupId = scanner.nextLine();
        String message = scanner.nextLine();

        ShareMessageRequest shareMessageRequest = new ShareMessageRequest();
        shareMessageRequest.setDestGroupId(groupId);
        shareMessageRequest.setMessage(message);

        channel.writeAndFlush(shareMessageRequest);
    }
}
