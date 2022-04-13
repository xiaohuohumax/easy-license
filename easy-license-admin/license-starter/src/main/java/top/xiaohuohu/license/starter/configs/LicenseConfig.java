package top.xiaohuohu.license.starter.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "license")
public class LicenseConfig {

    /**
     * 许可证文件路径
     */
    private String LicenseFilePath;

    /**
     * 公钥文件路径
     */
    private String publicKeyFilePath;
}
