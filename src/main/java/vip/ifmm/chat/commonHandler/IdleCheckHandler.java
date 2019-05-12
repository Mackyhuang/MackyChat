package vip.ifmm.chat.commonHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import vip.ifmm.chat.server.util.SessionCheck;

import java.util.concurrent.TimeUnit;

/**
 * 空闲检测
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/12 </p>
 */
public class IdleCheckHandler extends IdleStateHandler {

    private static final int IDLE_CHECK_TIME = 15;

    /**
     * 初始化空空闲检测
     * 参数依次是：
     * 读空闲时间、写空闲时间、读写空闲时间、时间单位
     */
    public IdleCheckHandler() {
        super(IDLE_CHECK_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(SessionCheck.getSession(ctx.channel()) + ", " + IDLE_CHECK_TIME + "秒内无响应，断开连接");
        ctx.channel().close();
    }
}
