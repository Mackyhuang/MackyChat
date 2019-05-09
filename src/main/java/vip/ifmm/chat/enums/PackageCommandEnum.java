package vip.ifmm.chat.enums;

public enum PackageCommandEnum {

    LOGIN_REQUEST(1),
    LOGIN_RESPONSE(2),
    MESSAGE_REQUEST(3),
    MESSAGE_RESPONSE(4),
    LOGOUT_REQUEST(5),
    LOGOUT_RESPONSE(6),
    GROUP_REQUEST(7),
    GROUP_RESPONSE(8);

    Byte code;

    PackageCommandEnum(Integer code) {
        this.code = (byte)(code & 0xFF);
    }

    public Byte getCode() {
        return code;
    }
}
