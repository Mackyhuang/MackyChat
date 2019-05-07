package vip.ifmm.chat.serializer;

import vip.ifmm.chat.serializer.impl.JsonSerializer;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    /**
     * 获取当前序列化器的代号
     * @return 当前序列化器的代号，位于枚举类中
     */
    byte SerializationAlgorithm();

    /**
     * 序列化方法
     * @param object 需要被序列号的对象
     * @return 序列化的结果——字节数组
     */
    byte[] serialize(Object object);

    /**
     * 反序列化方法
     * @param clazz 结果对象的Class信息
     * @param bytes 序列化字节数组
     * @param <T> 结果对象的类型
     * @return 结果对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
