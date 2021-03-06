package vip.ifmm.chat.protocol.request;

import vip.ifmm.chat.enums.PackageCommandEnum;
import vip.ifmm.chat.protocol.Package;

/**
 * 登录请求包
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/7 </p>
 */
public class LoginRequest extends Package {

    private String username;

    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Byte ByteCommand() {
        return PackageCommandEnum.LOGIN_REQUEST.getCode();
    }
}
