package top.xiaohuohu.license.web.starter;

import org.springframework.core.annotation.AliasFor;
import top.xiaohuohu.license.web.starter.adapters.InterceptorAdapter;
import top.xiaohuohu.license.web.starter.adapters.impl.InterceptorDefineAdapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LicenseInterceptor {

    @AliasFor("attrKey")
    String value() default "";

    @AliasFor("value")
    String attrKey() default "";

    /**
     * 拦截器实现
     *
     * @see InterceptorDefineAdapter
     */
    Class<? extends InterceptorAdapter> adapter() default InterceptorDefineAdapter.class;

    /**
     * 拦截返回错误提示
     */
    String msg() default "许可证未获得权限!";
}
