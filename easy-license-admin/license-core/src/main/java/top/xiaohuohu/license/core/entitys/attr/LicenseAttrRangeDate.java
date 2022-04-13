package top.xiaohuohu.license.core.entitys.attr;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class LicenseAttrRangeDate implements LicenseAttr {
    private Date start;
    private Date end;
}
