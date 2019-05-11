package vip.ifmm.chat.protocol.response;

import vip.ifmm.chat.enums.PackageCommandEnum;
import vip.ifmm.chat.protocol.Package;
import vip.ifmm.chat.server.util.Session;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/11 </p>
 */
public class ShareMessageResponse extends Package {

    private String sourceGroupId;

    private Session sourceUser;

    private String message;

    public String getSourceGroupId() {
        return sourceGroupId;
    }

    public void setSourceGroupId(String sourceGroupId) {
        this.sourceGroupId = sourceGroupId;
    }

    public Session getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(Session sourceUser) {
        this.sourceUser = sourceUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte ByteCommand() {
        return PackageCommandEnum.SHARE_MESSAGE_RESPONSE.getCode();
    }
}
