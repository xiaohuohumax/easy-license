package top.xiaohuohu.license.create;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "top.xiaohuohu.license")
public class LicenseCreateApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicenseCreateApplication.class, args);
    }

}
