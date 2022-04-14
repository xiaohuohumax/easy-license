package top.xiaohuohu.license.starter.events;

import top.xiaohuohu.license.core.entitys.License;

/**
 * 许可证解析事件
 */
public interface LicenseEvent {

    /**
     * 许可证内容改变触发（周期解析，不同则触发）
     */
    void loopParse(License newLicense);
}
