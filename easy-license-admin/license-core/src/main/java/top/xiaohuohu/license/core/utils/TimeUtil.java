package top.xiaohuohu.license.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    private final static String DEFINE_PATTERN = "yyyy-MM-dd HH:mm:ss";


    public static Date formatByPattern(String dateStr, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(dateStr);
    }

    public static Date formatByPattern(String dateStr) throws ParseException {
        return formatByPattern(dateStr, DEFINE_PATTERN);
    }


}
