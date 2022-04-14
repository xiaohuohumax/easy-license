package top.xiaohuohu.license.web.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.core.entitys.LicenseAttrItem;
import top.xiaohuohu.license.core.entitys.LicenseData;
import top.xiaohuohu.license.core.enums.LicenseStatus;
import top.xiaohuohu.license.core.utils.LicenseUtil;
import top.xiaohuohu.license.starter.EnableLicense;
import top.xiaohuohu.license.web.starter.EnableLicenseWebInterceptor;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

@Slf4j
@SpringBootApplication(scanBasePackages = {"top.xiaohuohu.license"})
@EnableLicense
@EnableLicenseWebInterceptor
public class WebDemoApplication implements CommandLineRunner {

    @Resource
    private License license;

    public static void main(String[] args) {
        SpringApplication.run(WebDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("测试demo启动成功");
        LicenseStatus status = license.getStatus();
        if (status.isAuthorized()) {
            log.info("许可证携带自定义参数如下:");
            LicenseData data = license.getData();
            Map<String, LicenseAttrItem> attrMap = data.getAttrMap();
            Set<String> keySet = attrMap.keySet();
            for (String key : keySet) {
                log.info("键: {} 值:{}", key, LicenseUtil.getAttr(data, key, attrMap.get(key).getAttrType().getTClass()));
            }
        }
    }
}
