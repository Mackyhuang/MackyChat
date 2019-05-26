package vip.ifmm.chat.serializer.impl;

import com.alibaba.fastjson.JSON;
import vip.ifmm.chat.enums.SerializationAlgorithmEnum;
import vip.ifmm.chat.serializer.Serializer;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class JsonSerializer implements Serializer {
    @Override
    public byte SerializationAlgorithm() {
        return SerializationAlgorithmEnum.JSON_SERIALIZER.getCode();
    }

    @Override
    public <T> byte[] serialize(T object) {
        return JSON.toJSONBytes(object);
    }


    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
