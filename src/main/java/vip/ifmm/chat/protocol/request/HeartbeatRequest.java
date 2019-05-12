package vip.ifmm.chat.protocol.request;

import vip.ifmm.chat.enums.PackageCommandEnum;
import vip.ifmm.chat.protocol.Package;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/12 </p>
 */
public class HeartbeatRequest extends Package {
    @Override
    public Byte ByteCommand() {
        return PackageCommandEnum.HEARTBEAT_REQUEST.getCode();
    }
}
