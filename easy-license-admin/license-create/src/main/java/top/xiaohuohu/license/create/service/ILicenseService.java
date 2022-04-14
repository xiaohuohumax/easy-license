package top.xiaohuohu.license.create.service;

import top.xiaohuohu.license.core.entitys.Keys;
import top.xiaohuohu.license.create.vo.DecryptLicenseVo;
import top.xiaohuohu.license.create.vo.EncryptLicenseVo;

/**
 * 许可证服务
 */
public interface ILicenseService {

    /**
     * 创建秘钥对
     */
    Keys createKeys();

    /**
     * 加密许可证
     */
    EncryptLicenseVo encryptLicenseByKey(EncryptLicenseVo encryptLicenseVo);

    /**
     * 解密许可证
     */
    DecryptLicenseVo decryptLicenseByKey(DecryptLicenseVo decryptLicenseVo);
}
