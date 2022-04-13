package top.xiaohuohu.license.core.entitys;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 密钥对
 */
@Data
@Accessors(chain = true)
public class Keys {
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 私钥
     */
    private String privateKey;
}
