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

