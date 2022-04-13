package top.xiaohuohu.license.web.starter.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
public class LicenseControllerConfig implements WebMvcConfigurer {

    private final LicenseControllerInterceptor interceptor;

    public LicenseControllerConfig(LicenseControllerInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

}