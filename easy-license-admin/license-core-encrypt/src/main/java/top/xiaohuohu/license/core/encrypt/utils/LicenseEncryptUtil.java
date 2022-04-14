package top.xiaohuohu.license.core.encrypt.utils;

import top.xiaohuohu.license.core.entitys.Keys;
import top.xiaohuohu.license.core.entitys.LicenseData;
import top.xiaohuohu.license.core.entitys.LicenseInfo;
import top.xiaohuohu.license.core.exceptions.LicenseException;
import top.xiaohuohu.license.core.utils.JsonParser;
import top.xiaohuohu.license.core.utils.RsaUtil;
import top.xiaohuohu.license.core.utils.ValidationUtil;

import java.util.List;

/**
 * 许可证加密工具类
 */
public class LicenseEncryptUtil {

    /**
     * 许可证加密
     *
     * @param keys        密钥对
     * @param licenseData 许可证信息
     * @return 加密许可证
     */
    public static String encrypt(Keys keys, LicenseData licenseData) throws Exception {
        List<String> validation = ValidationUtil.validation(licenseData);

        if (validation != null && !validation.isEmpty()) {
            throw new LicenseException(String.format("参数校验失败: %s", validation));
        }
        String unParse = JsonParser.unParse(licenseData);
        String encryptStr = RsaUtil.encrypt(keys.getPrivateKey(), unParse);

        LicenseInfo licenseInfo = new LicenseInfo()
                .setLicenseInfo(encryptStr)
                .setLicenseVisible(licenseData.getLicenseVisible());

        return JsonParser.unParseBeautify(licenseInfo);
    }

    /**
     * 创建密钥对
     *
     * @return 密钥对
     */
    public static Keys createKeys() {
        return RsaUtil.createKeys();
    }

}
