package top.xiaohuohu.license.starter;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Import;
import top.xiaohuohu.license.core.enums.MachineRegistration;
import top.xiaohuohu.license.starter.enums.LicenseInitFailModel;
import top.xiaohuohu.license.starter.enums.LicenseModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 许可证启动器
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({LicenseRegistrar.class})
@ImportAutoConfiguration(LicenseAutoConfig.class)
public @interface EnableLicense {

    /**
     * <b>许可证初始化模式</b>
     *
     * @see LicenseModel
     */
    LicenseModel model() default LicenseModel.LOCAL_PARSE;

    /**
     * <b>许可证初始化失败后执行模式</b>
     *
     * @see LicenseInitFailModel
     */
    LicenseInitFailModel initFailModel() default LicenseInitFailModel.PASS;

    /**
     * <b>许可证是否周期解析</b>
     */
    boolean loopParse() default true;

    /**
     * <b>周期解析 cron 表达式</b>
     */
    String loopParseCron() default "0 0 0 * * ?";

    /**
     * <b>获取注册码的方式</b>
     *
     * @see MachineRegistration
     */
    MachineRegistration registrationCodeModel() default MachineRegistration.AUTO;
}
