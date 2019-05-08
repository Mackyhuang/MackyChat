package vip.ifmm.chat.protocol;

/**
 * 定义包的规范
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public abstract class Package {

    Byte packageVersion = 1;

    public abstract Byte ByteCommand();
}
