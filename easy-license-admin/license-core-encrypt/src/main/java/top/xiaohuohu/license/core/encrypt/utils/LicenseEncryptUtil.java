package top.xiaohuohu.license.core.encrypt.utils;

import top.xiaohuohu.license.core.entitys.Keys;
import top.xiaohuohu.license.core.entitys.LicenseData;
import top.xiaohuohu.license.core.entitys.LicenseInfo;
import top.xiaohuohu.license.core.exceptions.LicenseException;
import top.xiaohuohu.license.core.utils.JsonParser;
import top.xiaohuohu.license.core.utils.LicenseUtil;
import top.xiaohuohu.license.core.utils.RsaUtil;
import top.xiaohuohu.license.core.utils.ValidationUtil;

import java.util.List;

public class LicenseEncryptUtil {

    public static String encrypt(Keys keys, LicenseData licenseData) throws Exception {
        List<String> validation = ValidationUtil.validation(licenseData);

        if (validation != null && !validation.isEmpty()) {
            throw new LicenseException(String.format("参数校验失败: %s", validation));
        }
        String unParse = LicenseUtil.unParse(licenseData);
        String encryptStr = RsaUtil.encrypt(keys.getPrivateKey(), unParse);

        LicenseInfo licenseInfo = new LicenseInfo()
                .setLicenseInfo(encryptStr)
                .setLicenseVisible(licenseData.getLicenseVisible());

        return JsonParser.unParseBeautify(licenseInfo);
    }

    public static Keys createKeys() {
        return RsaUtil.createKeys();
    }

}
