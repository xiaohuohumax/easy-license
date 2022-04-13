package top.xiaohuohu.license.core.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Util {

    public static String enMd(String sourceString) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        assert sourceString != null;
        byte[] source = sourceString.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(md5.digest(source));
    }
}
