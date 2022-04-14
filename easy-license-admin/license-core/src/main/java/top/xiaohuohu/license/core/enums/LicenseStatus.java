package top.xiaohuohu.license.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 许可证解析状态
 */
@Getter
public enum LicenseStatus {

    PARSE_FAIL(-99, "解析失败"),

    UNAUTHORIZED(-98, "未授权许可"),

    EXPIRED(-97, "许可证过期"),

    AUTHORIZED(0, "许可证验证通过");

    private final Integer code;
    private final String msg;

    LicenseStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonCreator
    public static LicenseStatus valueOf(Integer code) {
        LicenseStatus[] values = LicenseStatus.values();
        for (LicenseStatus value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    @JsonValue
    public Integer getValue() {
        return getCode();
    }


    /**
     * 判断许可证验证是否通过
     *
     * @return 许可证验证是否通过
     */
    public boolean isAuthorized() {
        return code >= 0;
    }
}
