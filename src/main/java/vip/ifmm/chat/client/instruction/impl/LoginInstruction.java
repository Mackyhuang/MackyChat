package vip.ifmm.chat.client.instruction.impl;

import io.netty.channel.Channel;
import vip.ifmm.chat.client.instruction.CommonInstruction;
import vip.ifmm.chat.protocol.request.LoginRequest;

import java.util.Scanner;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/9 </p>
 */
public class LoginInstruction implements CommonInstruction {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequest loginRequest = new LoginRequest();

        System.out.print("输入用户名登录: ");
        loginRequest.setUsername(scanner.nextLine());
        loginRequest.setPassword("pwd");

        // 发送登录数据包
        channel.writeAndFlush(loginRequest);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
