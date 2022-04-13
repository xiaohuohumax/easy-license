# easy-license springmvc接口拦截启动器

搭配 license-starter 使用,验证接口是否有调用许可

```text
web
 └── starter
     ├── adapters // 接口拦截适配
     │   ├── impl
     │   │   └── InterceptorDefineAdapter.java
     │   └── InterceptorAdapter.java
     ├── configs
     │   ├── LicenseControllerConfig.java
     │   └── LicenseControllerInterceptor.java
     ├── EnableLicenseWebInterceptor.java // 请求拦截注解(重要)
     └── LicenseInterceptor.java
```