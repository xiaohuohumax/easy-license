package top.xiaohuohu.license.core.entitys.attr;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 自定义参数: 字符串
 */
@Data
@Accessors(chain = true)
public class LicenseAttrString implements LicenseAttr {
    private String data;
}
