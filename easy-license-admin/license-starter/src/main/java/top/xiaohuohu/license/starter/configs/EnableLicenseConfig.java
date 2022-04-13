package top.xiaohuohu.license.starter.configs;

import lombok.Data;
import top.xiaohuohu.license.starter.enums.LicenseInitFailModel;
import top.xiaohuohu.license.starter.enums.LicenseModel;
import top.xiaohuohu.license.core.enums.MachineRegistration;

@Data
public class EnableLicenseConfig {

    private LicenseModel model;

    private LicenseInitFailModel initFailModel;

    private Boolean loopParse;

    private String loopParseCron;

    private MachineRegistration registrationCodeModel;
}
