package vip.ifmm.chat.serializer.impl;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import vip.ifmm.chat.enums.SerializationAlgorithmEnum;
import vip.ifmm.chat.serializer.Serializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于protostuff的序列化器
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/26 </p>
 */
public class ProtostuffSerializer implements Serializer {

    private Map<Class<?>, Schema<?>> cacheSchemaMap = new ConcurrentHashMap<>();

    private Objenesis objenesis = new ObjenesisStd(true);

    @Override
    public byte SerializationAlgorithm() {
        return SerializationAlgorithmEnum.PROTOSTUFF_SERIALIZER.getCode();
    }

    /**
     * 序列化（对象 -> 字节数组）
     */
    @Override
    public <T> byte[] serialize(T object) {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        Class<T> clazz = (Class<T>) object.getClass();
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(object, schema, buffer);
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化（字节数组 -> 对象）
     */
    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) {
        try {
            //替代getSchema(clazz).newMessage();
            T object = objenesis.newInstance(clazz);
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, object, schema);
            return object;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * Protostuff的Schema获取
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> Schema<T> getSchema(Class<T> clazz){
        return (Schema<T>) cacheSchemaMap.computeIfAbsent(clazz, cls -> RuntimeSchema.createFrom(cls));
    }

}
