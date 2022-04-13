package top.xiaohuohu.license.web.demo.controller;

import cn.hutool.core.io.FileUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiaohuohu.license.core.web.entitys.LResult;
import top.xiaohuohu.license.core.web.utils.ResultUtil;
import top.xiaohuohu.license.starter.adapter.LicenseParse;
import top.xiaohuohu.license.starter.configs.LicenseConfig;
import top.xiaohuohu.license.web.demo.vo.RefreshLicenseVo;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/license")
public class UpdateLicenseController {

    @Resource
    private LicenseParse licenseParse;

    @Resource
    private LicenseConfig licenseConfig;

    /**
     * 更新许可证文件
     */
    @PostMapping("/refresh")
    public LResult<String> refreshLicense(@RequestBody(required = false) @Validated RefreshLicenseVo refreshLicenseVo) {
        if (null != refreshLicenseVo) {
            // 替换许可证文件内容
            FileUtil.writeString(refreshLicenseVo.getLicenseInfo(),
                    licenseConfig.getLicenseFilePath(),
                    StandardCharsets.UTF_8);
        }

        // 触发解析
        licenseParse.loopParse();
        return ResultUtil.SUCCESS("刷新成功");
    }

}
