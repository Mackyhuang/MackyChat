package vip.ifmm.chat.protocol.request;

import vip.ifmm.chat.enums.PackageCommandEnum;
import vip.ifmm.chat.protocol.Package;

/**
 * 消息请求数据包
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class MessageRequest extends Package {

    private String message;

    public MessageRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte ByteCommand() {
        return PackageCommandEnum.MESSAGE_REQUEST.getCode();
    }
}
