package top.xiaohuohu.license.starter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import top.xiaohuohu.license.starter.adapter.LicenseParse;
import top.xiaohuohu.license.starter.configs.EnableLicenseConfig;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.starter.enums.LicenseInitFailModel;
import top.xiaohuohu.license.starter.events.LicenseEventPublisher;
import top.xiaohuohu.license.starter.jobs.LicenseLoopParseJob;

@Slf4j
@ConditionalOnMissingBean(LicenseAutoConfig.class)
@Import({
        LicenseParse.class, LicenseLoopParseJob.class, LicenseEventPublisher.class
})
public class LicenseAutoConfig extends UseApplicationContext {

    private final EnableLicenseConfig enableLicenseConfig;

    private final LicenseParse licenseParse;

    public LicenseAutoConfig(EnableLicenseConfig enableLicenseConfig, LicenseParse licenseParse) {
        this.enableLicenseConfig = enableLicenseConfig;
        this.licenseParse = licenseParse;
    }

    @Bean
    @ConditionalOnMissingBean(License.class)
    public License license() {
        log.info("许可证初始化!");
        LicenseInitFailModel initFailModel = enableLicenseConfig.getInitFailModel();
        License init = licenseParse.init();
        initFailModel.matchDo(applicationContext, init);
        log.info("许可证初始话成功!");
        return init;
    }
}
