package top.xiaohuohu.license.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import top.xiaohuohu.license.core.entitys.attr.*;
import top.xiaohuohu.license.core.exceptions.LicenseException;
import top.xiaohuohu.license.core.utils.TimeUtil;

import java.text.ParseException;
import java.util.Arrays;

/**
 * <b>许可证自定义参数类型以及解析方法</b>
 * 注意: 前端传入数据必须严格按照 formatData 解析要求
 */
@Getter
public enum LicenseAttrType {
    DOUBLE("DOUBLE", "浮点数", "格式: 1.0", LicenseAttrDouble.class) {
        /**
         * 浮点数类型<br/>
         * 格式: 11.0
         * */
        @Override
        public LicenseAttr formatDataItem(String attrStr) {
            return new LicenseAttrDouble().setData(Double.valueOf(attrStr));
        }
    },
    STRING("STRING", "字符串", "格式: abc", LicenseAttrString.class) {
        /**
         * 字符串类型<br/>
         * */
        @Override
        public LicenseAttr formatDataItem(String attrStr) {
            return new LicenseAttrString().setData(attrStr);
        }
    },
    DATE("DATE", "时间", "格式: yyyy-MM-dd HH:mm:ss", LicenseAttrDate.class) {
        /**
         * 时间类型<br/>
         * 格式: yyyy-MM-dd HH:mm:ss
         * */
        @Override
        public LicenseAttr formatDataItem(String attrStr) throws Exception {
            return new LicenseAttrDate().setDate(TimeUtil.formatByPattern(attrStr));
        }
    },
    STRING_LIST("STRING_LIST", "字符串列表", "格式: [1,2,3]", LicenseAttrStringList.class) {
        /**
         * 字符串数组类型<br/>
         * 格式: [1,2,3]
         * */
        @Override
        public LicenseAttr formatDataItem(String attrStr) {
            String substring = attrStr.substring(1, attrStr.length() - 1);
            LicenseAttrStringList licenseAttrStringList = new LicenseAttrStringList();
            licenseAttrStringList.setData(Arrays.asList(substring.split(",")));
            return licenseAttrStringList;
        }
    },
    RANGE_DOUBLE("RANGE_DOUBLE", "范围浮点数", "格式: [1.2,2.4]", LicenseAttrRangeDouble.class) {
        /**
         * 范围类型<br/>
         * 格式: [1.2,2.4]
         * */
        @Override
        public LicenseAttr formatDataItem(String attrStr) {
            String trim = attrStr.trim();
            String[] split = trim.substring(1, trim.length() - 1).split(",");
            return new LicenseAttrRangeDouble()
                    .setStart(Double.valueOf(split[0]))
                    .setEnd(Double.valueOf(split[1]));
        }
    },
    RANGE_DATE("RANGE_DATE", "范围日期", "格式: [yyyy-MM-dd HH:mm:ss,yyyy-MM-dd HH:mm:ss]", LicenseAttrRangeDate.class) {
        /**
         * 范围类型<br/>
         * 格式: [yyyy-MM-dd HH:mm:ss,yyyy-MM-dd HH:mm:ss]
         * */
        @Override
        public LicenseAttr formatDataItem(String attrStr) throws ParseException {
            String trim = attrStr.trim();
            String[] split = trim.substring(1, trim.length() - 1).split(",");
            return new LicenseAttrRangeDate()
                    .setStart(TimeUtil.formatByPattern(split[0]))
                    .setEnd(TimeUtil.formatByPattern(split[1]));
        }
    };

    private final String type;
    private final String msg;
    private final String formatTipMsg;

    private final Class<? extends LicenseAttr> tClass;

    LicenseAttrType(String type, String msg, String formatTipMsg, Class<? extends LicenseAttr> tClass) {
        this.type = type;
        this.msg = msg;
        this.formatTipMsg = formatTipMsg;
        this.tClass = tClass;
    }

    protected abstract LicenseAttr formatDataItem(String attrStr) throws Exception;

    /**
     * 格式字符串转换为自定义参数
     *
     * @param attrStr 格式字符串
     * @return 自定义参数
     */
    public LicenseAttr formatData(String attrStr) {
        try {
            return formatDataItem(attrStr);
        } catch (Exception ignore) {
            throw new LicenseException(String.format("%s类型解析错误,请确保格式正确 %s", getMsg(), getFormatTipMsg()));
        }
    }

    @JsonValue
    public String getValue() {
        return getType();
    }
}
