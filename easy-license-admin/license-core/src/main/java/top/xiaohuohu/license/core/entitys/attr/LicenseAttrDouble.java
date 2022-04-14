package top.xiaohuohu.license.core.entitys.attr;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 自定义参数: 浮点数
 */
@Data
@Accessors(chain = true)
public class LicenseAttrDouble implements LicenseAttr {
    private Double data;
}
