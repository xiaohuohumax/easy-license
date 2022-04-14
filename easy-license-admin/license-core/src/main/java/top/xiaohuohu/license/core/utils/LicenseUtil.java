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

    /**
     * 获取自定义参数,并且不做异常拦截
     *
     * @param licenseData 许可证携带数据
     * @param key         自定参数key
     * @param tClass      自定义参数类型
     * @return 自定义参数
     * @throws LicenseException 参数获取异常,类型错误等
     */
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

    /**
     * 获取自定义参数,并且异常拦截,异常时均为null
     *
     * @param licenseData 许可证携带数据
     * @param key         自定参数key
     * @param tClass      自定义参数类型
     * @return 自定义参数
     */
    public static <T extends LicenseAttr> T getAttr(LicenseData licenseData, String key, Class<T> tClass) {
        try {
            return getAttrThrowable(licenseData, key, tClass);
        } catch (Exception ignore) {
            return null;
        }
    }

    /**
     * 判断许可证 key 是否存在
     *
     * @param key         自定义参数key
     * @param licenseData 许可证携带数据
     * @return 是否存在key
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
