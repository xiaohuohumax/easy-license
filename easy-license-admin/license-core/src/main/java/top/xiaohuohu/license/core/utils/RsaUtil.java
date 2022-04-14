package top.xiaohuohu.license.core.utils;

import top.xiaohuohu.license.core.entitys.Keys;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA 工具
 * 注: 代码参考自 hutools RSA
 */
public class RsaUtil {

    private final static Integer KEY_SIZE = 1024;
    private static final KeyPairGenerator rsa;
    private static final KeyFactory keyFactory;
    private static final Cipher cipher;

    static {
        try {
            rsa = KeyPairGenerator.getInstance("RSA");
            keyFactory = KeyFactory.getInstance("RSA");
            cipher = Cipher.getInstance("RSA");

            rsa.initialize(KEY_SIZE);
        } catch (Exception ignore) {
            throw new RuntimeException("RSA 异常");
        }
    }

    private static String base64Encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private static byte[] base64Decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public static Keys createKeys() {
        try {
            KeyPair keyPair = rsa.generateKeyPair();

            return new Keys()
                    .setPrivateKey(base64Encode(keyPair.getPrivate().getEncoded()))
                    .setPublicKey(base64Encode(keyPair.getPublic().getEncoded()));
        } catch (Exception ignore) {
        }
        return null;
    }

    /**
     * 私钥加密
     *
     * @param privateKeyStr 私钥
     * @param data          数据
     * @return 加密结果
     */
    public static String encrypt(String privateKeyStr, String data) throws Exception {
        byte[] privateKeyByte = base64Decode(privateKeyStr);
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyByte));
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

        ByteArrayOutputStream out = doFinal(bytes, 117);
        return base64Encode(out.toByteArray());
    }

    private static ByteArrayOutputStream doFinal(byte[] data, int maxBlockSize) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int offSet = 0, inputLen = data.length;
        for (int remainLength = inputLen; remainLength > 0; remainLength = inputLen - offSet) {
            byte[] cache = cipher.doFinal(data, offSet, Math.min(remainLength, maxBlockSize));
            out.write(cache, 0, cache.length);
            offSet += maxBlockSize;
        }
        return out;
    }

    /**
     * 公钥解密
     *
     * @param publicKeyStr 公钥
     * @param data         数据
     * @return 解密数据
     */
    public static String decrypt(String publicKeyStr, String data) {
        try {
            byte[] publicKeyByte = base64Decode(publicKeyStr);
            byte[] dataBytes = base64Decode(data);
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyByte));
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            ByteArrayOutputStream out = doFinal(dataBytes, 128);
            return out.toString();
        } catch (Exception throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(String.format("解析许可证异常: %s", throwable.getMessage()));
        }
    }
}
