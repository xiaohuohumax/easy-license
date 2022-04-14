package top.xiaohuohu.license.core.entitys.attr;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 自定义参数: 日期
 */
@Data
@Accessors(chain = true)
public class LicenseAttrDate implements LicenseAttr {
    private Date date;
}
