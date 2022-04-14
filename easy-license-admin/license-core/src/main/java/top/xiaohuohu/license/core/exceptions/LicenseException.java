package top.xiaohuohu.license.core.exceptions;

/**
 * 许可证通用异常
 */
public class LicenseException extends RuntimeException {

    public LicenseException(String message) {
        super(message);
    }

    public LicenseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LicenseException(Throwable cause) {
        super(cause);
    }

}
