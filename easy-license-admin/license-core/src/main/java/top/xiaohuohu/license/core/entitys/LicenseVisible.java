package top.xiaohuohu.license.core.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 许可证可见信息
 */
@Data
public class LicenseVisible implements Serializable {

    /**
     * 许可证名称
     */
    @NotBlank(message = "许可证名称为空!")
    private String name;
    /**
     * 许可证描述
     */
    private String describe;

    /**
     * 版本
     */
    @NotBlank(message = "许可证版本信息为空!")
    private String version;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    private Date timeStamp = new Date();
}
