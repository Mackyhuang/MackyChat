package vip.ifmm.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vip.ifmm.chat.protocol.request.HeartbeatRequest;
import vip.ifmm.chat.protocol.response.HeartbeatResponse;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/12 </p>
 */
public class HeartbeatRequestHandler extends SimpleChannelInboundHandler<HeartbeatRequest> {

    public static final HeartbeatRequestHandler INSTANSE = new HeartbeatRequestHandler();

    private HeartbeatRequestHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartbeatRequest heartbeatRequest) throws Exception {
        channelHandlerContext.writeAndFlush(new HeartbeatResponse());
    }
}
