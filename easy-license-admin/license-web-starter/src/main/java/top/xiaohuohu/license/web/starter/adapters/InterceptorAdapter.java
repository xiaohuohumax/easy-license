package top.xiaohuohu.license.web.starter.adapters;

import org.springframework.web.method.HandlerMethod;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.web.starter.LicenseInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InterceptorAdapter {

    boolean doInterceptor(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod,
                          LicenseInterceptor methodAnnotation, License license) throws Exception;
}
