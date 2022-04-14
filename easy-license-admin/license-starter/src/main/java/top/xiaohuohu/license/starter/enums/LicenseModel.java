package top.xiaohuohu.license.starter.enums;

import lombok.Getter;
import top.xiaohuohu.license.starter.adapter.LicenseParseAdapter;
import top.xiaohuohu.license.starter.adapter.impl.LocalParseAdapter;

/**
 * 许可证启动器启动方式
 */
@Getter
public enum LicenseModel {

    LOCAL_PARSE(LocalParseAdapter.class, "解析本地授权文件启动");

    private final Class<? extends LicenseParseAdapter> adapter;

    private final String tipMsg;

    LicenseModel(Class<? extends LicenseParseAdapter> adapter, String tipMsg) {
        this.adapter = adapter;
        this.tipMsg = tipMsg;
    }

}
