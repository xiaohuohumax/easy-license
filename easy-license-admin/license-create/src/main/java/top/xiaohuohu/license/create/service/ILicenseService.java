package top.xiaohuohu.license.create.service;

import top.xiaohuohu.license.core.entitys.Keys;
import top.xiaohuohu.license.create.vo.DecryptLicenseVo;
import top.xiaohuohu.license.create.vo.EncryptLicenseVo;

public interface ILicenseService {

    Keys createKeys();

    EncryptLicenseVo encryptLicenseByKey(EncryptLicenseVo encryptLicenseVo);

    DecryptLicenseVo decryptLicenseByKey(DecryptLicenseVo decryptLicenseVo);
}
