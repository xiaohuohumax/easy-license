package top.xiaohuohu.license.core.web.exceptions;

import lombok.Getter;
import top.xiaohuohu.license.core.web.enums.ResultCode;

/**
 * 通用异常
 */
@Getter
public class WebException extends RuntimeException {

    /**
     * 状态码
     */
    private final ResultCode resultCode;

    public WebException(String message) {
        super(message);
        this.resultCode = ResultCode.FAIL;
    }

    public WebException(String message, ResultCode resultCode) {
        super(message);
        this.resultCode = resultCode;
    }

    public WebException(String message, Throwable cause, ResultCode resultCode) {
        super(message, cause);
        this.resultCode = resultCode;
    }

    public WebException(Throwable cause, ResultCode resultCode) {
        super(cause);
        this.resultCode = resultCode;
    }
}
