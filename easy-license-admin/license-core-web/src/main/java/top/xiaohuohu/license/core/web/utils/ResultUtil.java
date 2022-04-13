package top.xiaohuohu.license.core.web.utils;

import top.xiaohuohu.license.core.web.entitys.LResult;
import top.xiaohuohu.license.core.web.enums.ResultCode;

public class ResultUtil {

    public static <T> LResult<T> SUCCESS(T data, String msg) {
        return new LResult<T>()
                .setCode(ResultCode.SUCCESS)
                .setMsg(msg)
                .setData(data);
    }

    public static <T> LResult<T> SUCCESS(T data) {
        return SUCCESS(data, ResultCode.SUCCESS.getMsg());
    }

    public static <T> LResult<T> FAIL(T data, String msg) {
        return new LResult<T>()
                .setCode(ResultCode.FAIL)
                .setMsg(msg)
                .setData(data);
    }

    public static <T> LResult<T> FAIL(T data) {
        return FAIL(data, ResultCode.FAIL.getMsg());
    }

    public static <T> LResult<T> ANY(ResultCode resultCode, T data, String msg) {
        return new LResult<T>()
                .setCode(resultCode)
                .setMsg(msg)
                .setData(data);
    }
}
