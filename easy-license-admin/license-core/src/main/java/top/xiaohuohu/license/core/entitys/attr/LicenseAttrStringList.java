package top.xiaohuohu.license.core.entitys.attr;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 自定义参数: 字符串列表
 */
@Data
@Accessors(chain = true)
public class LicenseAttrStringList implements LicenseAttr {
    private List<String> data;
}
