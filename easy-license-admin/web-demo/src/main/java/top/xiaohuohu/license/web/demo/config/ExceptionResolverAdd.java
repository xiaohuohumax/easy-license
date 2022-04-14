package top.xiaohuohu.license.web.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xiaohuohu.license.core.exceptions.LicenseException;
import top.xiaohuohu.license.core.web.entitys.LResult;
import top.xiaohuohu.license.core.web.utils.ResultUtil;
import top.xiaohuohu.license.web.starter.exceptions.LicenseInterceptorException;

/**
 * 补充全局异常拦截
 */
@Slf4j
@ControllerAdvice
public class ExceptionResolverAdd {

    @ExceptionHandler(LicenseException.class)
    @ResponseBody
    public LResult<String> handleLicenseException(LicenseException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.FAIL(null, e.getMessage());
    }

    @ExceptionHandler(LicenseInterceptorException.class)
    @ResponseBody
    public LResult<String> handleLicenseInterceptorException(LicenseInterceptorException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.FAIL(null, e.getMessage());
    }
}