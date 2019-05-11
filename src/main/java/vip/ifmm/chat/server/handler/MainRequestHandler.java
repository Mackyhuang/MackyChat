package vip.ifmm.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.enums.PackageCommandEnum;
import vip.ifmm.chat.protocol.Package;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/11 </p>
 */
@ChannelHandler.Sharable
public class MainRequestHandler extends SimpleChannelInboundHandler<Package> {

    public static final MainRequestHandler INSTANCE = new MainRequestHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Package>> handlerMap;

    private MainRequestHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(PackageCommandEnum.MESSAGE_REQUEST.getCode(), MessageRequestHandler.INSTANCE);
        handlerMap.put(PackageCommandEnum.LOGOUT_REQUEST.getCode(), LogoutRequestHandler.INSTANCE);
        handlerMap.put(PackageCommandEnum.GROUP_REQUEST.getCode(), GroupRequestHandler.INSTANCE);
        handlerMap.put(PackageCommandEnum.JOIN_REQUEST.getCode(), JoinRequestHandler.INSTANCE);
        handlerMap.put(PackageCommandEnum.QUIT_REQUEST.getCode(), QuitRequestHandler.INSTANCE);
        handlerMap.put(PackageCommandEnum.LIST_REQUEST.getCode(), ListRequestHandler.INSTANCE);
        handlerMap.put(PackageCommandEnum.SHARE_MESSAGE_REQUEST.getCode(), ShareMessageRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Package pack) throws Exception {
        handlerMap.get(pack.ByteCommand()).channelRead(channelHandlerContext, pack);
    }
}
