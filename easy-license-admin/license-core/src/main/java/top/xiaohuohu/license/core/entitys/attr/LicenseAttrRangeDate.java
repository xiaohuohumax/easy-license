package top.xiaohuohu.license.core.entitys.attr;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 自定义参数: 范围日期
 */
@Data
@Accessors(chain = true)
public class LicenseAttrRangeDate implements LicenseAttr {
    private Date start;
    private Date end;
}
