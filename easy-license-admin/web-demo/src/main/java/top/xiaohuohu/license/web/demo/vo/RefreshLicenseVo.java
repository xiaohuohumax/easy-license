package top.xiaohuohu.license.web.demo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class RefreshLicenseVo implements Serializable {

    @NotBlank(message = "许可证信息为空!")
    private String licenseInfo;
}
