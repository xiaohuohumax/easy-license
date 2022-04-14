package top.xiaohuohu.license.core.web.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xiaohuohu.license.core.web.entitys.LResult;
import top.xiaohuohu.license.core.web.exceptions.WebException;
import top.xiaohuohu.license.core.web.utils.ResultUtil;

import java.util.stream.Collectors;

/**
 * 全局异常拦截
 */
@Slf4j
@ControllerAdvice
public class ExceptionResolver {

    /**
     * 通用异常
     */
    @ExceptionHandler(WebException.class)
    @ResponseBody
    public LResult<String> handleWebException(WebException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.ANY(e.getResultCode(), null, e.getMessage());
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public LResult<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);

        String errorStr = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(";"));

        return ResultUtil.FAIL(null, errorStr);
    }

    /**
     * 意外异常
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public LResult<String> handleThrowable(Throwable e) {
        log.error(e.getMessage(), e);
        return ResultUtil.FAIL("操作异常");
    }
}