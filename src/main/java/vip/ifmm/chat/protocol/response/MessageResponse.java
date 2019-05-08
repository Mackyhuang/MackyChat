package vip.ifmm.chat.protocol.response;

import vip.ifmm.chat.enums.PackageCommandEnum;
import vip.ifmm.chat.protocol.Package;

/**
 * 消息应答数据包
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class MessageResponse extends Package {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte ByteCommand() {
        return PackageCommandEnum.MESSAGE_RESPONSE.getCode();
    }
}
