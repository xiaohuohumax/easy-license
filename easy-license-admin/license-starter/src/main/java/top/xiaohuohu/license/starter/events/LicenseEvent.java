package top.xiaohuohu.license.starter.events;

import top.xiaohuohu.license.core.entitys.License;

public interface LicenseEvent {

    void loopParse(License newLicense);
}
