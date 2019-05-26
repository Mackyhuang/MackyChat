package vip.ifmm.chat.enums;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public enum  SerializationAlgorithmEnum {

    JSON_SERIALIZER(1),
    PROTOSTUFF_SERIALIZER(2);

    Byte code;

    SerializationAlgorithmEnum(Integer code) {
        this.code = (byte)(code & 0xFF);
    }

    public Byte getCode() {
        return code;
    }
}
