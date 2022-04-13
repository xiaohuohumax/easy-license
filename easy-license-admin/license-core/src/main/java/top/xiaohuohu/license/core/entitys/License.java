package top.xiaohuohu.license.core.entitys;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import top.xiaohuohu.license.core.enums.LicenseStatus;
import top.xiaohuohu.license.core.enums.MachineRegistration;

import java.io.Serializable;

/**
 * 许可证解析信息
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class License implements Serializable {
    /**
     * 解析状态
     */
    private LicenseStatus status;
    /**
     * 许可信息
     */
    private LicenseData data;
    /**
     * 解析提示
     */
    private String msg;
    /**
     * 本机注册码
     */
    private String registrationCode = MachineRegistration.AUTO.getCode();

}
