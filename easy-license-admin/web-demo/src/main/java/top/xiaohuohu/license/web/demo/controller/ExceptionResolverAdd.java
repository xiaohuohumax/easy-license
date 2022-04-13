package top.xiaohuohu.license.web.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xiaohuohu.license.core.exceptions.LicenseException;
import top.xiaohuohu.license.core.web.entitys.LResult;
import top.xiaohuohu.license.core.web.utils.ResultUtil;

@Slf4j
@ControllerAdvice
public class ExceptionResolverAdd {

    @ExceptionHandler(LicenseException.class)
    @ResponseBody
    public LResult<String> handleLicenseWebException(LicenseException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.FAIL( null, e.getMessage());
    }
}