package vip.ifmm.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import vip.ifmm.chat.client.handler.*;
import vip.ifmm.chat.client.instruction.InstructionSelector;
import vip.ifmm.chat.client.instruction.impl.LoginInstruction;
import vip.ifmm.chat.protocol.packageProcess.PackageDecoder;
import vip.ifmm.chat.protocol.packageProcess.PackageEncoder;
import vip.ifmm.chat.protocol.packageProcess.Spliter;
import vip.ifmm.chat.protocol.request.LoginRequest;
import vip.ifmm.chat.protocol.request.MessageRequest;
import vip.ifmm.chat.server.handler.ShareMessageRequestHandler;
import vip.ifmm.chat.server.util.LoginCheck;
import vip.ifmm.chat.server.util.SessionCheck;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 客户端
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class MackyChatClient {
    //重连次数
    private static final int MAX_RETRY = 5;
    //服务器IP地址
    private static final String HOST = "127.0.0.1";
    //服务器端口
    private static final int PORT = 8023;

    public void mackyChatClient() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new Spliter());
                        channel.pipeline().addLast(new PackageDecoder());
                        channel.pipeline().addLast(new LoginResponseHandler());
                        channel.pipeline().addLast(new MessageResponseHandler());
                        channel.pipeline().addLast(new ShareMessageResponseHandler());
                        channel.pipeline().addLast(new GroupResponseHandler());
                        channel.pipeline().addLast(new JoinResponseHandler());
                        channel.pipeline().addLast(new QuitResponseHandler());
                        channel.pipeline().addLast(new ListResponseHandler());
                        channel.pipeline().addLast(new LogoutResponseHandler());
                        channel.pipeline().addLast(new PackageEncoder());
                    }
                });
        connectServer(bootstrap, MAX_RETRY);
    }

    private void connectServer(Bootstrap bootstrap, int retry) {
        bootstrap.connect(HOST, PORT).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                Channel channel = ((ChannelFuture) future).channel();
                consoleChatOpen(channel);
            } else if (retry == 0) {
                System.out.println(String.format("重新连接%d次依旧失败，已放弃重新连接", MAX_RETRY));
            } else {
                int currentTry = (MAX_RETRY - retry) + 1;
                int delay = currentTry << 1;
                System.out.println(new Date() + String.format(": 连接失败，马上开始第%d次重新连接", currentTry));
                bootstrap.config().group().schedule(() -> connectServer(bootstrap, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    /**
     * 客户端控制台输入流程控制
     */
    private void consoleChatOpen(Channel channel) {
        LoginInstruction loginInstruction = new LoginInstruction();
        InstructionSelector instructionSelector = new InstructionSelector();
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionCheck.checkLogin(channel)) {
                    loginInstruction.exec(scanner, channel);
                }else {
                    instructionSelector.exec(scanner, channel);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new MackyChatClient().mackyChatClient();
    }
}
