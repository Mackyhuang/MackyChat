package vip.ifmm.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import vip.ifmm.chat.protocol.packageProcess.PackageDecoder;
import vip.ifmm.chat.protocol.packageProcess.PackageEncoder;
import vip.ifmm.chat.protocol.packageProcess.Spliter;
import vip.ifmm.chat.server.handler.*;
import vip.ifmm.chat.server.util.SessionCheck;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * 服务端
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class MackyChatServer {

    private static final int PORT = 8023;

    public void mackyChatServer(){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new Spliter());
                        channel.pipeline().addLast(new PackageDecoder());
                        channel.pipeline().addLast(new LoginRequestHandler());
                        channel.pipeline().addLast(new VerifyHandler());
                        channel.pipeline().addLast(new MessageRequestHandler());
                        channel.pipeline().addLast(new GroupRequestHandler());
                        channel.pipeline().addLast(new JoinRequestHandler());
                        channel.pipeline().addLast(new QuitRequestHandler());
                        channel.pipeline().addLast(new ListRequestHandler());
                        channel.pipeline().addLast(new LogoutRequestHandler());
                        channel.pipeline().addLast(new PackageEncoder());
                    }
                });
        bindPort(bootstrap);
        onlineCheck();
    }

    private void bindPort(ServerBootstrap bootstrap){
        bootstrap.bind(PORT).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + PORT + "]绑定成功!");
            } else {
                System.err.println("端口[" + PORT + "]绑定失败!");
            }
        });
    }

    private void onlineCheck(){
            new Thread(() -> {
                while (true){
                    System.out.println(new Date() + "- 在线用户：");
                    Iterator<String> iterator = SessionCheck.userChannelMap.keySet().iterator();
                    while (iterator.hasNext()){
                        System.out.println(String.format("[%s] - ", iterator.next()));
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
    }

    public static void main(String[] args) {
        new MackyChatServer().mackyChatServer();
    }
}
