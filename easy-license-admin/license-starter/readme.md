# easy-license 许可证启动器

```text
starter
 ├── adapter // 解析模式适配
 │   ├── impl
 │   │   └── LocalParseAdapter.java
 │   ├── LicenseParse.java
 │   └── LicenseParseAdapter.java
 ├── configs
 │   ├── EnableLicenseConfig.java
 │   └── LicenseConfig.java //  启动器配置
 ├── EnableLicense.java // 启动器注解(重要)
 ├── enums
 │   ├── LicenseInitFailModel.java // 解析失败模式
 │   └── LicenseModel.java // 解析模式
 ├── events
 │   ├── EventOrder.java
 │   ├── LicenseEvent.java
 │   ├── LicenseEventListener.java // 解析事件监听
 │   └── LicenseEventPublisher.java // 解析事件发布
 ├── jobs
 │   └── LicenseLoopParseJob.java // 定时解析
 ├── LicenseAutoConfig.java
 ├── LicenseRegistrar.java
 └── UseApplicationContext.java
```