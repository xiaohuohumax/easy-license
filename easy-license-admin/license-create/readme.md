# easy-license 许可证后台

```text
create
 ├── controller
 │   └── LicenseController.java // 生成许可证相关
 ├── LicenseCreateApplication.java
 ├── service
 │   ├── ILicenseService.java
 │   └── impl
 │       └── LicenseServiceImpl.java
 └── vo
     ├── DecryptLicenseVo.java
     └── EncryptLicenseVo.java
```

注意: 调用接口即可生成许可证,密钥对等等