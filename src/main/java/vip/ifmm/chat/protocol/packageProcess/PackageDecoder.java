package vip.ifmm.chat.protocol.packageProcess;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import vip.ifmm.chat.protocol.PackagePicker;

import java.util.List;

/**
 * 数据包的解码
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class PackageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PackagePicker.PICKER.decode(byteBuf));
    }
}
