package top.xiaohuohu.license.core.entitys.attr;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LicenseAttrRangeDouble implements LicenseAttr {
    private Double start;
    private Double end;
}
