package top.xiaohuohu.license.starter.adapter;

import top.xiaohuohu.license.core.entitys.License;

/**
 * 许可证解析适配器
 */
public interface LicenseParseAdapter {

    /**
     * 初始化执行
     */
    License init();

    /**
     * 每天执行解析
     */
    void loopParse();

}
