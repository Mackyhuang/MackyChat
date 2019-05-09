package vip.ifmm.chat.client.instruction;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/9 </p>
 */
public interface CommonInstruction {
    void exec(Scanner scanner, Channel channel);
}
