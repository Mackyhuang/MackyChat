package vip.ifmm.chat.enums;

public enum PackageCommandEnum {

    LOGIN_REQUEST(1),
    LOGIN_RESPONSE(2),
    MESSAGE_REQUEST(3),
    MESSAGE_RESPONSE(4);

    Byte code;

    PackageCommandEnum(Integer code) {
        this.code = (byte)(code & 0xFF);
    }

    public Byte getCode() {
        return code;
    }
}
