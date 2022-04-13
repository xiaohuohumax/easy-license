package top.xiaohuohu.license.core.web.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(0, "success"),
    FAIL(-1, "fail");

    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonCreator
    public static ResultCode valueOf(Integer code) {
        ResultCode[] values = ResultCode.values();
        for (ResultCode value : values) {
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
}
