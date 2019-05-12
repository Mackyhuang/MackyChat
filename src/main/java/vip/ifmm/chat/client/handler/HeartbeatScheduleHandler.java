package vip.ifmm.chat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import vip.ifmm.chat.protocol.request.HeartbeatRequest;

import java.util.concurrent.TimeUnit;

/**
 * 客户端发送心跳检测的地方
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/12 </p>
 */
public class HeartbeatScheduleHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //开启心跳
        scheduleDoHeartbeat(ctx);

        super.channelActive(ctx);
    }

    private void scheduleDoHeartbeat(ChannelHandlerContext ctx){
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()){
                ctx.channel().writeAndFlush(new HeartbeatRequest());
                scheduleDoHeartbeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
