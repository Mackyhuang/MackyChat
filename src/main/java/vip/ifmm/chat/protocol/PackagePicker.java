package vip.ifmm.chat.protocol;

import io.netty.buffer.ByteBuf;
import vip.ifmm.chat.enums.PackageCommandEnum;
import vip.ifmm.chat.enums.SerializationAlgorithmEnum;
import vip.ifmm.chat.protocol.request.LoginRequest;
import vip.ifmm.chat.protocol.request.MessageRequest;
import vip.ifmm.chat.protocol.response.LoginResponse;
import vip.ifmm.chat.protocol.response.MessageResponse;
import vip.ifmm.chat.serializer.Serializer;
import vip.ifmm.chat.serializer.impl.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义协议
 *
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class PackagePicker {

    //饿汉单例
    public final static PackagePicker PICKER = new PackagePicker();

    //魔术
    public final static Integer MAGIC_NUMBER = 0xaccababe;

    //协议包存储仓
    private static final Map<Byte, Class<? extends Package>> packageStore;

    //序列化器存储仓
    private static final Map<Byte, Serializer> serializerStore;

    static {
        //初始化协议包的存储仓
        packageStore = new HashMap<>();
        //初始化序列化器的存储仓
        serializerStore = new HashMap<>();

        packageStore.put(PackageCommandEnum.LOGIN_REQUEST.getCode(), LoginRequest.class);
        packageStore.put(PackageCommandEnum.LOGIN_REQUEST.getCode(), LoginResponse.class);
        packageStore.put(PackageCommandEnum.MESSAGE_REQUEST.getCode(), MessageRequest.class);
        packageStore.put(PackageCommandEnum.MESSAGE_RESPONSE.getCode(), MessageResponse.class);

        Serializer jsonSerializer = new JsonSerializer();
        serializerStore.put(SerializationAlgorithmEnum.JSON_SERIALIZER.getCode(), jsonSerializer);
    }

    /**
     * 协议编码器
     * @param byteBuf
     * @param pack
     */
    public void encode(ByteBuf byteBuf, Package pack){
        //序列号协议中的数据包部分
        byte[] body = Serializer.DEFAULT.serialize(pack);
        //构建完整的协议
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(pack.packageVersion);
        byteBuf.writeByte(Serializer.DEFAULT.SerializationAlgorithm());
        byteBuf.writeByte(pack.ByteCommand());
        byteBuf.writeInt(body.length);
        byteBuf.writeBytes(body);
    }

    /**
     * 协议解码器
     * @param byteBuf
     */
    public Package decode(ByteBuf byteBuf){
        //魔术无需在此处验证
        byteBuf.skipBytes(4);
        //版本号无需验证
        byteBuf.skipBytes(1);
        //获取序列化算法
        byte serializationAlgorithm = byteBuf.readByte();
        //获取指令
        byte packageCommand = byteBuf.readByte();
        //数据包长度
        int len = byteBuf.readInt();
        //读取数据
        byte[] body = new byte[len];
        byteBuf.readBytes(body);

        //数据反序列化
        Serializer serializer = serializerAdapter(serializationAlgorithm);
        Class<? extends Package> pack = packageAdapter(packageCommand);
        if (serializer != null && pack != null){
            return serializer.deserialize(pack, body);
        }
        return null;
    }

    private Serializer serializerAdapter(byte serializationAlgorithm){
        return serializerStore.get(serializationAlgorithm);
    }

    private Class<? extends Package> packageAdapter(byte packageCommand){
        return packageStore.get(packageCommand);
    }
}
