# easy-license 基础模块


```text
core
 ├── entitys
 │   ├── attr // 许可证自定义参数
 │   │   ├── LicenseAttr.java
 │   │   ├── LicenseAttrDate.java
 │   │   ├── LicenseAttrDouble.java
 │   │   ├── LicenseAttrRangeDate.java
 │   │   ├── LicenseAttrRangeDouble.java
 │   │   ├── LicenseAttrString.java
 │   │   └── LicenseAttrStringList.java
 │   ├── Keys.java // 密钥对
 │   ├── License.java // 许可证
 │   ├── LicenseAttrItem.java // 许可证自定义参数
 │   ├── LicenseData.java // 许可证携带数据
 │   ├── LicenseInfo.java // 许可证json实体格式
 │   └── LicenseVisible.java // 许可证可见参数
 ├── enums
 │   ├── LicenseAttrType.java // 许可证参数解析
 │   ├── LicenseStatus.java // 许可证解析状态
 │   └── MachineRegistration.java // 机器注册码
 ├── exceptions
 │   └── LicenseException.java // 许可证基础异常
 └── utils // 工具类
     ├── CommandUtil.java // 命令行工具
     ├── JsonParser.java // jackson 解析
     ├── LicenseUtil.java // 许可证工具
     ├── MD5Util.java // MD5 工具
     ├── RsaUtil.java // rsa 工具
     ├── TimeUtil.java // 时间工具
     └── ValidationUtil.java // 参数校验工具
```