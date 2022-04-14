package top.xiaohuohu.license.create;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "top.xiaohuohu.license")
public class LicenseCreateApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LicenseCreateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("许可证后台服务启动成功");
    }
}
