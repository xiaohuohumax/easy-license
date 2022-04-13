package top.xiaohuohu.license.core.web.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xiaohuohu.license.core.web.entitys.LResult;
import top.xiaohuohu.license.core.web.exceptions.WebException;
import top.xiaohuohu.license.core.web.utils.ResultUtil;

@Slf4j
@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public LResult<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.FAIL(null,"参数缺失");
    }

    @ExceptionHandler(WebException.class)
    @ResponseBody
    public LResult<String> handleLicenseWebException(WebException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.ANY(e.getResultCode(), null, e.getMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseBody
    public LResult<String> handleJsonProcessingException(JsonProcessingException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.FAIL(null, String.format("请求参数转换错误,请确保参数正确: %s", e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public LResult<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Throwable cause = e.getCause();
        if (cause instanceof JsonProcessingException) {
            return handleJsonProcessingException((JsonProcessingException) cause);
        }
        return handleThrowable(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public LResult<String> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultUtil.FAIL("操作异常");
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public LResult<String> handleThrowable(Throwable e) {
        log.error(e.getMessage(), e);
        return ResultUtil.FAIL("操作异常");
    }
}