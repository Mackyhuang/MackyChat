package vip.ifmm.chat.protocol;

import io.netty.buffer.ByteBuf;
import vip.ifmm.chat.enums.PackageCommandEnum;
import vip.ifmm.chat.enums.SerializationAlgorithmEnum;
import vip.ifmm.chat.protocol.request.*;
import vip.ifmm.chat.protocol.response.*;
import vip.ifmm.chat.serializer.Serializer;
import vip.ifmm.chat.serializer.impl.JsonSerializer;
import vip.ifmm.chat.serializer.impl.ProtostuffSerializer;

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

    //魔数
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
        packageStore.put(PackageCommandEnum.LOGIN_RESPONSE.getCode(), LoginResponse.class);
        packageStore.put(PackageCommandEnum.MESSAGE_REQUEST.getCode(), MessageRequest.class);
        packageStore.put(PackageCommandEnum.MESSAGE_RESPONSE.getCode(), MessageResponse.class);
        packageStore.put(PackageCommandEnum.LOGOUT_REQUEST.getCode(), LogoutRequest.class);
        packageStore.put(PackageCommandEnum.LOGOUT_RESPONSE.getCode(), LogoutResponse.class);
        packageStore.put(PackageCommandEnum.GROUP_REQUEST.getCode(), GroupRequest.class);
        packageStore.put(PackageCommandEnum.GROUP_RESPONSE.getCode(), GroupResponse.class);
        packageStore.put(PackageCommandEnum.JOIN_REQUEST.getCode(), JoinRequest.class);
        packageStore.put(PackageCommandEnum.JOIN_RESPONSE.getCode(), JoinResponse.class);
        packageStore.put(PackageCommandEnum.QUIT_REQUEST.getCode(), QuitRequest.class);
        packageStore.put(PackageCommandEnum.QUIT_RESPONSE.getCode(), QuitResponse.class);
        packageStore.put(PackageCommandEnum.LIST_REQUEST.getCode(), ListRequest.class);
        packageStore.put(PackageCommandEnum.LIST_RESPONSE.getCode(), ListResponse.class);
        packageStore.put(PackageCommandEnum.SHARE_MESSAGE_REQUEST.getCode(), ShareMessageRequest.class);
        packageStore.put(PackageCommandEnum.SHARE_MESSAGE_RESPONSE.getCode(), ShareMessageResponse.class);
        packageStore.put(PackageCommandEnum.HEARTBEAT_REQUEST.getCode(), HeartbeatRequest.class);
        packageStore.put(PackageCommandEnum.HEARTBEAT_RESPONSE.getCode(), HeartbeatResponse.class);

        Serializer jsonSerializer = new JsonSerializer();
        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
        serializerStore.put(SerializationAlgorithmEnum.JSON_SERIALIZER.getCode(), jsonSerializer);
        serializerStore.put(SerializationAlgorithmEnum.PROTOSTUFF_SERIALIZER.getCode(), protostuffSerializer);
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
