package top.xiaohuohu.license.web.starter.adapters.impl;

import org.springframework.web.method.HandlerMethod;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.core.enums.LicenseStatus;
import top.xiaohuohu.license.core.utils.LicenseUtil;
import top.xiaohuohu.license.web.starter.LicenseInterceptor;
import top.xiaohuohu.license.web.starter.adapters.InterceptorAdapter;
import top.xiaohuohu.license.web.starter.exceptions.LicenseInterceptorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认通过判断是否存在key作为拦截条件
 */
public class InterceptorDefineAdapter implements InterceptorAdapter {

    @Override
    public boolean doInterceptor(HttpServletRequest request, HttpServletResponse response,
                                 HandlerMethod handlerMethod, LicenseInterceptor methodAnnotation,
                                 License license) {

        LicenseStatus status = license.getStatus();

        if (status.isAuthorized()) {
            // 授权成功 检查是否存在key
            if (!LicenseUtil.hasKey(license.getData(), methodAnnotation.attrKey())) {
                // 缺少key
                throw new LicenseInterceptorException(methodAnnotation.msg());
            }
        } else {
            // 授权失败
            throw new LicenseInterceptorException(license.getMsg());
        }
        return true;
    }
}
