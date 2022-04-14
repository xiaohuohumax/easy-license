package top.xiaohuohu.license.core.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * md5工具
 */
public class MD5Util {

    /**
     * md5加密
     *
     * @param sourceString 待价密字符串
     * @return md5字符串
     */
    public static String enMd(String sourceString) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        assert sourceString != null;
        byte[] source = sourceString.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(md5.digest(source));
    }
}
