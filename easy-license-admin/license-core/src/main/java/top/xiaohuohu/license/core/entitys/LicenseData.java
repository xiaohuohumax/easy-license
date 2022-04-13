package top.xiaohuohu.license.core.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 许可证数据
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class LicenseData implements Serializable {

    /**
     * 可见信息
     */
    @Valid
    @NotNull(message = "许可证可见信息为空")
    private LicenseVisible licenseVisible;
    /**
     * 许可证信息
     */
    @Valid
    private Map<String, LicenseAttrItem> attrMap;

    /**
     * 许可证的允许的注册码
     */
    @NotEmpty(message = "许可证注册码为空")
    private List<String> authRegistrationCodeList;

    /**
     * 授权过期时间
     */
    @NotNull(message = "过期时间为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    private Date expiredTime;

}
