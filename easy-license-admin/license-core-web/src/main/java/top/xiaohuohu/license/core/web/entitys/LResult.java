package top.xiaohuohu.license.core.web.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import top.xiaohuohu.license.core.web.enums.ResultCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用返回
 */
@Data
@Accessors(chain = true)
public class LResult<T> implements Serializable {
    /**
     * 状态码
     */
    private ResultCode code;
    /**
     * 数据
     */
    private T data;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    private Date timeStamp = new Date();

}
