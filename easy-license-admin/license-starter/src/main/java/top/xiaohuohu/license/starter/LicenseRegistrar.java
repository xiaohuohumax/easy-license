package top.xiaohuohu.license.starter;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import top.xiaohuohu.license.starter.configs.EnableLicenseConfig;

@ConditionalOnMissingBean
public class LicenseRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Class<?> annotationType = EnableLicense.class;
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(
                annotationType.getName(), false));
        assert attributes != null;

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(EnableLicenseConfig.class);

        builder.addPropertyValue("model", attributes.getEnum("model"));
        builder.addPropertyValue("initFailModel", attributes.getEnum("initFailModel"));
        builder.addPropertyValue("loopParse", attributes.getBoolean("loopParse"));
        builder.addPropertyValue("loopParseCron", attributes.getString("loopParseCron"));
        builder.addPropertyValue("registrationCodeModel", attributes.getEnum("registrationCodeModel"));

        registry.registerBeanDefinition("enableLicenseConfig", builder.getBeanDefinition());
    }

}