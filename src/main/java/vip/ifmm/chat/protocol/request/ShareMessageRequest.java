package vip.ifmm.chat.protocol.request;

import vip.ifmm.chat.enums.PackageCommandEnum;
import vip.ifmm.chat.protocol.Package;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/11 </p>
 */
public class ShareMessageRequest extends Package {

    private String destGroupId;

    private String message;

    public String getDestGroupId() {
        return destGroupId;
    }

    public void setDestGroupId(String destGroupId) {
        this.destGroupId = destGroupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte ByteCommand() {
        return PackageCommandEnum.SHARE_MESSAGE_REQUEST.getCode();
    }
}
