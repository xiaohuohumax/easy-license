package top.xiaohuohu.license.web.starter.adapters;

import org.springframework.web.method.HandlerMethod;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.web.starter.LicenseInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 许可证请求接口拦截规则
 */
public interface InterceptorAdapter {

    boolean doInterceptor(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod,
                          LicenseInterceptor methodAnnotation, License license) throws Exception;
}
