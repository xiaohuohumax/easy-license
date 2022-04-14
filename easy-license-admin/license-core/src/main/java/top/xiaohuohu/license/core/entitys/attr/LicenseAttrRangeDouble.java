package top.xiaohuohu.license.core.entitys.attr;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 自定义参数: 范围浮点数
 */
@Data
@Accessors(chain = true)
public class LicenseAttrRangeDouble implements LicenseAttr {
    private Double start;
    private Double end;
}
