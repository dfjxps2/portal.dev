# 部署说明
## Tomcat设置
- tomcat.util.scan.StandardJarScanFilter.jarsToSkip<br>
将TOMCAT/conf/catalina.properties文件中的
```
tomcat.util.scan.StandardJarScanFilter.jarsToSkip=\
```
改为
```
tomcat.util.scan.StandardJarScanFilter.jarsToSkip=*,\
```

## WEB-INF/classes/properties/config.properties
### ca.skip.verification
=true	跳过证书用户认证过程中的从CA查询用户特征码的步骤，用于离线测试。<br>
=false	执行证书用户认证过程中的从CA查询用户特征码的步骤，用于正式运行。<br>


