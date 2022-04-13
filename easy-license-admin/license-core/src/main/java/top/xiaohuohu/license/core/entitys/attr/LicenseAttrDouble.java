package top.xiaohuohu.license.core.entitys.attr;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LicenseAttrDouble implements LicenseAttr {
    private Double data;
}
