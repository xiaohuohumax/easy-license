package top.xiaohuohu.license.web.starter.exceptions;

import top.xiaohuohu.license.core.exceptions.LicenseException;

/**
 * 请求接口许可证拦截异常
 */
public class LicenseInterceptorException extends LicenseException {

    public LicenseInterceptorException(String message) {
        super(message);
    }

    public LicenseInterceptorException(String message, Throwable cause) {
        super(message, cause);
    }

    public LicenseInterceptorException(Throwable cause) {
        super(cause);
    }
}
