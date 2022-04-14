package top.xiaohuohu.license.core.entitys;

import lombok.Data;
import top.xiaohuohu.license.core.enums.LicenseAttrType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 许可证自定义参数
 */
@Data
public class LicenseAttrItem implements Serializable {
    /**
     * 自定义参数类型
     */
    @NotNull(message = "自定义参数类型为空")
    private LicenseAttrType attrType;
    /**
     * 自定义参数
     */
    @NotBlank(message = "自定义参数为空")
    private String attrStr;

    /**
     * 自定义参数描述
     */
    private String describe;
}
