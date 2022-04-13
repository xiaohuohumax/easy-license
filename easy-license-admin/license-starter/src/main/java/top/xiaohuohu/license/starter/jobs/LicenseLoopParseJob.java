package top.xiaohuohu.license.starter.jobs;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import top.xiaohuohu.license.starter.adapter.LicenseParse;
import top.xiaohuohu.license.starter.configs.EnableLicenseConfig;

@EnableScheduling
public class LicenseLoopParseJob implements SchedulingConfigurer {

    private final EnableLicenseConfig enableLicenseConfig;

    private final LicenseParse licenseParse;

    public LicenseLoopParseJob(EnableLicenseConfig enableLicenseConfig, LicenseParse licenseParse) {
        this.enableLicenseConfig = enableLicenseConfig;
        this.licenseParse = licenseParse;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (!enableLicenseConfig.getLoopParse()) {
            return;
        }
        taskRegistrar.addTriggerTask(
                licenseParse::loopParse,
                triggerContext -> new CronTrigger(enableLicenseConfig.getLoopParseCron())
                        .nextExecutionTime(triggerContext)
        );
    }

}