package top.xiaohuohu.license.web.starter.adapters.impl;

import org.springframework.web.method.HandlerMethod;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.core.enums.LicenseStatus;
import top.xiaohuohu.license.core.exceptions.LicenseException;
import top.xiaohuohu.license.core.utils.LicenseUtil;
import top.xiaohuohu.license.web.starter.LicenseInterceptor;
import top.xiaohuohu.license.web.starter.adapters.InterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorDefineAdapter implements InterceptorAdapter {

    @Override
    public boolean doInterceptor(HttpServletRequest request, HttpServletResponse response,
                                 HandlerMethod handlerMethod, LicenseInterceptor methodAnnotation,
                                 License license) {

        LicenseStatus status = license.getStatus();

        if (status.isAuthorized()) {
            // 授权成功 校验自定义参数
            if (!LicenseUtil.hasKey(license.getData(), methodAnnotation.attrKey())) {
                // 缺少自定参数
                // throw new LicenseWebException(methodAnnotation.msg(), ResultCode.FAIL);
                throw new LicenseException(methodAnnotation.msg());
            }
        } else {
            // 授权失败
            throw new LicenseException(license.getMsg());
            // throw new LicenseWebException(license.getMsg(), ResultCode.FAIL);
        }
        return true;
    }
}
