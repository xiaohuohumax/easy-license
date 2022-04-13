package top.xiaohuohu.license.create.vo;

import lombok.Data;
import top.xiaohuohu.license.core.entitys.Keys;
import top.xiaohuohu.license.core.entitys.LicenseData;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class EncryptLicenseVo implements Serializable {

    /**
     * 许可证密钥
     */
    @NotNull(message = "钥匙对为空!")
    private Keys keys;
    /**
     * 许可证信息
     */
    @Valid
    @NotNull(message = "许可证信息为空!")
    private LicenseData licenseData;
    /**
     * 许可证内容
     */
    private String licenseEncryptData;
}
