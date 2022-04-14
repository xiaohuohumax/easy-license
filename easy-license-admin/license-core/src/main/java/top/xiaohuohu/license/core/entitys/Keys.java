package top.xiaohuohu.license.core.entitys;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 密钥对
 */
@Data
@Accessors(chain = true)
public class Keys implements Serializable {
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 私钥
     */
    private String privateKey;
}
