package top.xiaohuohu.license.starter.events;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import top.xiaohuohu.license.starter.UseApplicationContext;
import top.xiaohuohu.license.core.entitys.License;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class LicenseEventPublisher extends UseApplicationContext implements LicenseEvent {

    @Override
    public void loopParse(License newLicense) {
        // 有更新才触发监听事件
        License oldLicense = getOldLicense();
        if (!ObjectUtils.isEmpty(oldLicense)
                && !ObjectUtils.isEmpty(newLicense)
                && !oldLicense.equals(newLicense)) {
            // 触发事件
            log.info("许可证内容变更,事件触发");
            List<LicenseEventListener> licenseEventListenerList = getLicenseEventListenerList();
            if (!CollectionUtils.isEmpty(licenseEventListenerList)) {
                for (LicenseEventListener licenseEventListener : licenseEventListenerList) {
                    try {
                        licenseEventListener.loopParse(newLicense);
                    } catch (Exception ignore) {
                    }
                }
            }

            // 更新全局bean
            BeanUtils.copyProperties(newLicense, oldLicense);
        }

    }

    private License getOldLicense() {
        return applicationContext.getBean(License.class);
    }

    public List<LicenseEventListener> getLicenseEventListenerList() {

        Map<String, LicenseEventListener> beansOfType = applicationContext.getBeansOfType(LicenseEventListener.class);

        return beansOfType.values().stream()
                .sorted(Comparator.comparing(LicenseEventListener::getOrder))
                .collect(Collectors.toList());
    }


}
