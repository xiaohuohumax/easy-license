package top.xiaohuohu.license.web.starter.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.xiaohuohu.license.core.entitys.License;
import top.xiaohuohu.license.core.exceptions.LicenseException;
import top.xiaohuohu.license.web.starter.LicenseInterceptor;
import top.xiaohuohu.license.web.starter.adapters.InterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class LicenseControllerInterceptor implements HandlerInterceptor, ApplicationContextAware {

    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private final License license;

    public LicenseControllerInterceptor(License license) {
        this.license = license;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            LicenseInterceptor methodAnnotation = handlerMethod.getMethodAnnotation(LicenseInterceptor.class);
            if (ObjectUtils.isEmpty(methodAnnotation)) {
                return true;
            }
            Class<? extends InterceptorAdapter> adapter = methodAnnotation.adapter();

            InterceptorAdapter interceptorAdapter = getInterceptorAdapter(adapter);
            if (null == interceptorAdapter) {
                log.warn("许可证请求拦截适配器未找到实例 {}", adapter.getName());
                // throw new LicenseWebException("许可证请求拦截适配器未找到实例", ResultCode.FAIL);
                throw new LicenseException("许可证请求拦截适配器未找到实例");
            }

            return interceptorAdapter.doInterceptor(request, response, handlerMethod, methodAnnotation, license);
        }
        return true;
    }

    private InterceptorAdapter getInterceptorAdapter(Class<? extends InterceptorAdapter> adapter) {
        Map<String, ? extends InterceptorAdapter> beansOfType = applicationContext.getBeansOfType(adapter);

        ArrayList<? extends InterceptorAdapter> interceptorAdapters = new ArrayList<>(beansOfType.values());

        if (CollectionUtils.isEmpty(interceptorAdapters)) {
            return null;
        }
        return interceptorAdapters.get(0);

    }
}