package top.xiaohuohu.license.create.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiaohuohu.license.core.entitys.Keys;
import top.xiaohuohu.license.core.web.entitys.LResult;
import top.xiaohuohu.license.core.web.utils.ResultUtil;
import top.xiaohuohu.license.create.service.ILicenseService;
import top.xiaohuohu.license.create.vo.DecryptLicenseVo;
import top.xiaohuohu.license.create.vo.EncryptLicenseVo;

@RestController
@RequestMapping("/license")
public class LicenseController {

    @Autowired
    private ILicenseService licenseService;

    /**
     * 生成密钥对
     */
    @PostMapping("/create/key")
    public LResult<Keys> createKeys() {
        return ResultUtil.SUCCESS(licenseService.createKeys());
    }

    /**
     * 通过密钥对和许可信息创建许可证
     */
    @PostMapping("/encrypt/license/by/key")
    public LResult<EncryptLicenseVo> encryptLicenseByKey(@RequestBody @Validated EncryptLicenseVo encryptLicenseVo) {
        return ResultUtil.SUCCESS(licenseService.encryptLicenseByKey(encryptLicenseVo));
    }

    /**
     * 通过密钥对解析许可证获取许可内容
     */
    @PostMapping("/decrypt/license/by/key")
    public LResult<DecryptLicenseVo> decryptLicenseByKey(@RequestBody @Validated DecryptLicenseVo decryptLicenseVo) {
        return ResultUtil.SUCCESS(licenseService.decryptLicenseByKey(decryptLicenseVo));
    }
}
