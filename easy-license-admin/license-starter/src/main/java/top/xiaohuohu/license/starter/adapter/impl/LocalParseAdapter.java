package top.xiaohuohu.license.starter.adapter.impl;

import lombok.extern.slf4j.Slf4j;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.core.enums.MachineRegistration;
import top.xiaohuohu.license.starter.UseApplicationContext;
import top.xiaohuohu.license.starter.adapter.LicenseParseAdapter;
import top.xiaohuohu.license.starter.configs.EnableLicenseConfig;
import top.xiaohuohu.license.starter.configs.LicenseConfig;
import top.xiaohuohu.license.starter.events.LicenseEventPublisher;
import top.xioahuohu.license.core.decrypt.utils.LicenseDecryptUtil;

/**
 * 许可证本地解析适配器
 */
@Slf4j
public class LocalParseAdapter extends UseApplicationContext implements LicenseParseAdapter {

    private final LicenseEventPublisher licenseEventPublisher;
    private final EnableLicenseConfig enableLicenseConfig;
    private final LicenseConfig licenseConfig;

    public LocalParseAdapter(LicenseEventPublisher licenseEventPublisher, EnableLicenseConfig enableLicenseConfig, LicenseConfig licenseConfig) {
        this.licenseEventPublisher = licenseEventPublisher;
        this.enableLicenseConfig = enableLicenseConfig;
        this.licenseConfig = licenseConfig;
    }

    @Override
    public License init() {
        MachineRegistration registrationCodeModel = enableLicenseConfig.getRegistrationCodeModel();

        String code = registrationCodeModel.getCode();
        String licenseFilePath = licenseConfig.getLicenseFilePath();

        String publicKeyFilePath = licenseConfig.getPublicKeyFilePath();
        License license = LicenseDecryptUtil.decrypt(licenseFilePath, publicKeyFilePath, code);
        license.setRegistrationCode(code);
        return license;
    }

    @Override
    public void loopParse() {
        License newLicense = init();
        // 触发事件
        licenseEventPublisher.loopParse(newLicense);
    }

}
