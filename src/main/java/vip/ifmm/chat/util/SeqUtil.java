package vip.ifmm.chat.util;

import java.util.UUID;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/9 </p>
 */
public class SeqUtil {

    public static String getSeq(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}
