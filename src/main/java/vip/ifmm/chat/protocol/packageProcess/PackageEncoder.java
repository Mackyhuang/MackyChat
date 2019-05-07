package vip.ifmm.chat.protocol.packageProcess;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import vip.ifmm.chat.protocol.Package;
import vip.ifmm.chat.protocol.PackagePicker;

/**
 * 数据包的编码
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class PackageEncoder extends MessageToByteEncoder<Package> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Package pack, ByteBuf byteBuf) throws Exception {
        PackagePicker.PICKER.encode(byteBuf, pack);
    }
}
