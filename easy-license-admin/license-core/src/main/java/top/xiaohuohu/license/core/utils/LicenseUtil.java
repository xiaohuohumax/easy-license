package top.xiaohuohu.license.core.utils;

import lombok.extern.slf4j.Slf4j;
import top.xiaohuohu.license.core.entitys.LicenseAttrItem;
import top.xiaohuohu.license.core.entitys.LicenseData;
import top.xiaohuohu.license.core.entitys.attr.LicenseAttr;
import top.xiaohuohu.license.core.enums.LicenseAttrType;
import top.xiaohuohu.license.core.exceptions.LicenseException;

import java.util.Map;

/**
 * 授权工具
 */
@Slf4j
public class LicenseUtil {

    public static <T extends LicenseAttr> T getAttrThrowable(LicenseData licenseData, String key, Class<T> tClass) throws Exception {
        try {
            if (licenseData == null || licenseData.getAttrMap() == null) {
                return null;
            }
            LicenseAttrItem licenseAttrItem = licenseData.getAttrMap().get(key);
            LicenseAttrType attrType = licenseAttrItem.getAttrType();
            LicenseAttr result = attrType.formatData(licenseAttrItem.getAttrStr());
            return tClass.cast(result);
        } catch (Exception e) {
            log.error("许可证自定义参数解析[{}]失败! {}", key, e.getMessage(), e);
            throw new LicenseException(String.format("许可证自定义参数解析[%s]失败! %s", key, e.getMessage()));
        }
    }

    public static <T extends LicenseAttr> T getAttr(LicenseData licenseData, String key, Class<T> tClass) {
        try {
            return getAttrThrowable(licenseData, key, tClass);
        } catch (Exception ignore) {
            return null;
        }
    }

    public static <T> T parse(String data, Class<T> tClass) {
        return JsonParser.parse(data, tClass);
    }

    public static <T> String unParse(T obj) {
        return JsonParser.unParse(obj);
    }

    public static <T> String unParseBeautify(T obj) {
        return JsonParser.unParseBeautify(obj);
    }

    /**
     * 判断 key 是否存在
     */
    public static boolean hasKey(LicenseData licenseData, String key) {
        if (null == licenseData || null == key) {
            return false;
        }
        Map<String, LicenseAttrItem> info = licenseData.getAttrMap();
        if (null == info) {
            return false;
        }
        return null != info.get(key);
    }
}
