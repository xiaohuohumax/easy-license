package top.xiaohuohu.license.core.entitys;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 许可证文件内容 json
 */
@Data
@Accessors(chain = true)
public class LicenseInfo implements Serializable {
    /**
     * 可见信息
     */
    private LicenseVisible licenseVisible;
    /**
     * 许可证
     */
    private String licenseInfo;
}
