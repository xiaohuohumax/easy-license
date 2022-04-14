package top.xiaohuohu.license.web.demo.event;

import org.springframework.stereotype.Component;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.starter.events.LicenseEventListener;

/**
 * 许可证解析事件监听
 */
@Component
public class LicenseEvent implements LicenseEventListener {
    @Override
    public void loopParse(License newLicense) {
        // 解析事件触发 许可证内容有所修改
        System.out.println(newLicense);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
