package top.xiaohuohu.license.create.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.xiaohuohu.license.core.encrypt.utils.LicenseEncryptUtil;
import top.xiaohuohu.license.core.entitys.Keys;
import top.xiaohuohu.license.core.entitys.LicenseAttrItem;
import top.xiaohuohu.license.core.entitys.LicenseData;
import top.xiaohuohu.license.core.utils.LicenseUtil;
import top.xiaohuohu.license.core.web.exceptions.WebException;
import top.xiaohuohu.license.create.service.ILicenseService;
import top.xiaohuohu.license.create.vo.DecryptLicenseVo;
import top.xiaohuohu.license.create.vo.EncryptLicenseVo;
import top.xioahuohu.license.core.decrypt.utils.LicenseDecryptUtil;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class LicenseServiceImpl implements ILicenseService {

    @Override
    public Keys createKeys() {
        return LicenseEncryptUtil.createKeys();
    }

    @Override
    public EncryptLicenseVo encryptLicenseByKey(EncryptLicenseVo encryptLicenseVo) {
        try {
            Keys keys = encryptLicenseVo.getKeys();

            if(StringUtils.isEmpty(keys.getPrivateKey())){
                throw new WebException("私钥不能为空");
            }

            LicenseData licenseData = encryptLicenseVo.getLicenseData();

            // 检查参数是否能够解析出来
            Map<String, LicenseAttrItem> attrMap = licenseData.getAttrMap();
            Set<String> keySet = attrMap.keySet();

            for (String key : keySet) {
                LicenseUtil.getAttrThrowable(licenseData, key, attrMap.get(key).getAttrType().getTClass());
            }
            encryptLicenseVo.setLicenseEncryptData(LicenseEncryptUtil.encrypt(keys, licenseData));
            return encryptLicenseVo;
        } catch (Exception e) {
            log.error("生成许可证错误! 原因:{}", e.getMessage(), e);
            throw new WebException(String.format("生成许可证错误! 原因:%s", e.getMessage()));
        }
    }

    @Override
    public DecryptLicenseVo decryptLicenseByKey(DecryptLicenseVo decryptLicenseVo) {
        try {
            Keys keys = decryptLicenseVo.getKeys();
            String licenseEncryptData = decryptLicenseVo.getLicenseEncryptData();

            LicenseData licenseData = LicenseDecryptUtil.decrypt(keys, licenseEncryptData);
            decryptLicenseVo.setLicenseData(licenseData);
            return decryptLicenseVo;
        } catch (Exception e) {
            log.error("生成许解析错误! 原因:{}", e.getMessage(), e);
            throw new WebException(String.format("生成许解析错误! 原因:%s", e.getMessage()));
        }
    }
}
