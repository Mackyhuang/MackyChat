package vip.ifmm.chat.protocol.packageProcess;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import vip.ifmm.chat.protocol.Package;
import vip.ifmm.chat.protocol.PackagePicker;

import java.util.List;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/11 </p>
 */
@ChannelHandler.Sharable
public class PackageCoder extends MessageToMessageCodec<ByteBuf, Package> {

    public static final PackageCoder CODER = new PackageCoder();

    private PackageCoder(){

    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Package pack, List<Object> list) throws Exception {
        ByteBuf byteBuf = channelHandlerContext.channel().alloc().buffer();
        PackagePicker.PICKER.encode(byteBuf, pack);
        list.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Package pack = PackagePicker.PICKER.decode(byteBuf);
        list.add(pack);
    }
}
