package top.xioahuohu.license.core.decrypt.utils;

import top.xiaohuohu.license.core.entitys.Keys;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.core.entitys.LicenseData;
import top.xiaohuohu.license.core.entitys.LicenseInfo;
import top.xiaohuohu.license.core.enums.LicenseStatus;
import top.xiaohuohu.license.core.exceptions.LicenseException;
import top.xiaohuohu.license.core.utils.LicenseUtil;
import top.xiaohuohu.license.core.utils.RsaUtil;
import top.xiaohuohu.license.core.utils.ValidationUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

public class LicenseDecryptUtil {

    private static String readFile(String filePath, String errMsg) {
        try {
            StringBuilder result = new StringBuilder();
            try (FileReader fileReader = new FileReader(new File(filePath));
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String tmpStr;
                while ((tmpStr = bufferedReader.readLine()) != null) {
                    result.append(tmpStr);
                }
            }
            return result.toString();
        } catch (Exception e) {
            throw new LicenseException(String.format("%s: %s", errMsg, e.getLocalizedMessage()));
        }
    }

    public static LicenseData decrypt(Keys keys, String licenseInfoStr) {
        LicenseInfo licenseInfo = LicenseUtil.parse(licenseInfoStr, LicenseInfo.class);

        String decryptStr = RsaUtil.decrypt(keys.getPublicKey(), licenseInfo.getLicenseInfo());

        return LicenseUtil.parse(decryptStr, LicenseData.class);
    }

    public static License decrypt(String licenseFilePath, String publicKeyFilePath, String registrationCode) {
        License result = new License().setRegistrationCode(registrationCode);
        try {
            String data = readFile(licenseFilePath, "许可证文件读取失败");
            String publicKeyStr = readFile(publicKeyFilePath, "公钥文件读取失败");
            Keys keys = new Keys().setPublicKey(publicKeyStr);

            LicenseData licenseData = decrypt(keys, data);
            // 校验参数
            List<String> validation = ValidationUtil.validation(licenseData);

            if (validation != null && validation.size() > 0) {
                throw new LicenseException(String.format("参数校验失败: %s", validation));
            }

            // 检查注册码
            List<String> authCodeList = licenseData.getAuthRegistrationCodeList();

            String code = authCodeList.stream()
                    .filter(registrationCode::equals)
                    .findAny().orElse(null);

            // 未授权
            if (code == null) {
                return result.setStatus(LicenseStatus.UNAUTHORIZED)
                        .setMsg(LicenseStatus.UNAUTHORIZED.getMsg());
            }

            Date expiredTime = licenseData.getExpiredTime();

            // 授权过期
            if (expiredTime.before(new Date())) {
                return result.setStatus(LicenseStatus.EXPIRED)
                        .setMsg(LicenseStatus.EXPIRED.getMsg());
            }

            result.setStatus(LicenseStatus.AUTHORIZED)
                    .setMsg(LicenseStatus.AUTHORIZED.getMsg())
                    .setData(licenseData);
        } catch (Exception e) {
            result.setStatus(LicenseStatus.PARSE_FAIL)
                    .setMsg(String.format("%s: %s", LicenseStatus.PARSE_FAIL.getMsg(), e.getMessage()));
        }
        return result;
    }
}
