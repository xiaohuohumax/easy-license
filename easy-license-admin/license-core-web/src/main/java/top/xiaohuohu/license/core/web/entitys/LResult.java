package top.xiaohuohu.license.core.web.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import top.xiaohuohu.license.core.web.enums.ResultCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class LResult<T> implements Serializable {
    private ResultCode code;
    private T data;
    private String msg;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timeStamp = LocalDateTime.now();

}
