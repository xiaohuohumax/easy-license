package top.xiaohuohu.license.starter.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.core.enums.LicenseStatus;
import top.xiaohuohu.license.core.exceptions.LicenseException;
import top.xiaohuohu.license.starter.adapter.impl.LocalParseAdapter;
import top.xiaohuohu.license.starter.configs.EnableLicenseConfig;

import java.util.List;

@Slf4j
@Import({LocalParseAdapter.class})
public class LicenseParse implements LicenseParseAdapter {

    private final EnableLicenseConfig enableLicenseConfig;

    private final LicenseParseAdapter licenseParseAdapter;

    public LicenseParse(List<LicenseParseAdapter> licenseParseAdapterMap,
                        EnableLicenseConfig enableLicenseConfig) {
        this.enableLicenseConfig = enableLicenseConfig;

        this.licenseParseAdapter = getLicenseParseAdapter(licenseParseAdapterMap);
    }

    private LicenseParseAdapter getLicenseParseAdapter(List<LicenseParseAdapter> licenseParseAdapterMap) {
        Class<? extends LicenseParseAdapter> adapter = enableLicenseConfig.getModel().getAdapter();

        for (LicenseParseAdapter value : licenseParseAdapterMap) {
            if (value.getClass().isAssignableFrom(adapter)) {
                return value;
            }
        }
        throw new LicenseException(String.format("许可证未找到相应的适配器 %s", adapter.getName()));
    }

    @Override
    public License init() {
        String code = enableLicenseConfig.getRegistrationCodeModel().getCode();
        License result = new License()
                .setStatus(LicenseStatus.PARSE_FAIL)
                .setMsg(LicenseStatus.PARSE_FAIL.getMsg())
                .setRegistrationCode(code);
        log.info(String.format("本机注册码:%s", code));
        try {
            result = licenseParseAdapter.init();
        } catch (Exception ignore) {
        }

        return result;
    }

    @Override
    public void loopParse() {
        licenseParseAdapter.loopParse();
    }
}
