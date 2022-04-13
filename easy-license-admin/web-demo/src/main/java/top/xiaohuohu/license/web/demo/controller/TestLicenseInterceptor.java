package top.xiaohuohu.license.web.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiaohuohu.license.core.web.entitys.LResult;
import top.xiaohuohu.license.core.web.utils.ResultUtil;
import top.xiaohuohu.license.web.starter.LicenseInterceptor;

@RestController
@RequestMapping("/test")
public class TestLicenseInterceptor {

    /**
     * 拦截请求接口
     */
    @PostMapping("/query/check")
    @LicenseInterceptor("user")
    public LResult<String> queryCheck() {
        return ResultUtil.SUCCESS("查询成功");
    }

    /**
     * 普通接口
     */
    @PostMapping("/query/uncheck")
    public LResult<String> queryUncheck() {
        return ResultUtil.SUCCESS("查询成功");
    }


}
