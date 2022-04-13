package top.xiaohuohu.license.starter.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.core.enums.LicenseStatus;

/**
 * 许可证初始化失败处理模式
 */
@Slf4j
@Getter
public enum LicenseInitFailModel {
    BLOCK("BLOCK", "程序即将阻塞") {
        @Override
        public Boolean isMatch(LicenseStatus licenseStatus) {
            return licenseStatus.getCode() < 0;
        }

        @Override
        @SuppressWarnings("all")
        public void matchDo(ApplicationContext applicationContext, License license) {
            if (isMatch(license.getStatus())) {
                log.error("许可证初始化失败![{}] 失败原因: {}", this.getTipMsg(), license.getMsg());
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ignore) {
                    }
                }
            }
        }
    },
    PASS("PASS", "允许通过") {
        @Override
        public Boolean isMatch(LicenseStatus licenseStatus) {
            return  licenseStatus.getCode() < 0;
        }

        @Override
        public void matchDo(ApplicationContext applicationContext, License license) {
            if (isMatch(license.getStatus())) {
                log.warn("许可证初始化失败![{}] 失败原因: {}", this.getTipMsg(), license.getMsg());
            }
        }
    },
    END("END", "程序即将结束") {
        @Override
        public Boolean isMatch(LicenseStatus licenseStatus) {
            return licenseStatus.getCode() < 0;
        }

        @Override
        public void matchDo(ApplicationContext applicationContext, License license) {
            if (isMatch(license.getStatus())) {
                log.error("许可证初始化失败![{}] 失败原因: {}", this.getTipMsg(), license.getMsg());
                System.exit(SpringApplication.exit(applicationContext));
            }
        }
    };


    LicenseInitFailModel(String initFailModelName, String tipMsg) {
        this.initFailModelName = initFailModelName;
        this.tipMsg = tipMsg;
    }

    private final String initFailModelName;
    private final String tipMsg;

    protected Boolean isMatch(LicenseStatus licenseStatus) {
        return false;
    }

    public abstract void matchDo(ApplicationContext applicationContext, License license);

}
