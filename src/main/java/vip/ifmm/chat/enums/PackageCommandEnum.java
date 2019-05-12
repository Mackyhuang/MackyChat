package vip.ifmm.chat.enums;

public enum PackageCommandEnum {

    LOGIN_REQUEST(1),
    LOGIN_RESPONSE(2),
    MESSAGE_REQUEST(3),
    MESSAGE_RESPONSE(4),
    LOGOUT_REQUEST(5),
    LOGOUT_RESPONSE(6),
    GROUP_REQUEST(7),
    GROUP_RESPONSE(8),
    JOIN_REQUEST(9),
    JOIN_RESPONSE(10),
    QUIT_REQUEST(11),
    QUIT_RESPONSE(12),
    LIST_REQUEST(13),
    LIST_RESPONSE(14),
    SHARE_MESSAGE_REQUEST(15),
    SHARE_MESSAGE_RESPONSE(16),
    HEARTBEAT_REQUEST(17),
    HEARTBEAT_RESPONSE(18);

    Byte code;

    PackageCommandEnum(Integer code) {
        this.code = (byte)(code & 0xFF);
    }

    public Byte getCode() {
        return code;
    }
}
