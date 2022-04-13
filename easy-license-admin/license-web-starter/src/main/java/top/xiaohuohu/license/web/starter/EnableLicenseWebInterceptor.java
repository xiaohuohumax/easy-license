package top.xiaohuohu.license.web.starter;

import org.springframework.context.annotation.Import;
import top.xiaohuohu.license.web.starter.adapters.impl.InterceptorDefineAdapter;
import top.xiaohuohu.license.web.starter.configs.LicenseControllerConfig;
import top.xiaohuohu.license.web.starter.configs.LicenseControllerInterceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>许可证请求拦截启动器</b>
 * 注意:必须搭配 @EnableLicense 注解 否则无法启动
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({LicenseControllerConfig.class, LicenseControllerInterceptor.class, InterceptorDefineAdapter.class})
public @interface EnableLicenseWebInterceptor {

}
