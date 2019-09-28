# Spring Cloud Config
​		原文作者为CSDN博主「方志朋」，遵循 [CC 4.0 BY-SA](https://creativecommons.org/licenses/by-sa/4.0/) 版权协议，转载请附上原文出处链接及本声明。[原文链接](https://blog.csdn.net/forezp/article/details/81041028)  
​		**本笔记在此基础上修改整理。**

## 一、简介
​		分布式配置中心组件**Spring Cloud Config**可以方便在分布式系统中服务配置文件统一管理。它支持配置服务放在本地，也支持放在远程Git仓库中。  
​		在**Spring Cloud Config**组件中，分两个角色，**Config Server**和**Config Client**。

## 二、构建Config Server
1. 创建一个父项目**spring-cloud-demo**，**pom.xml**如下:
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-demo</artifactId>
        <groupId>com.iflytek</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>config-client</artifactId>
    <packaging>jar</packaging>

    <name>config-client</name>
    <description>Demo project for Spring Cloud Config Client</description>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
    </dependencies>
</project>
```
2. 创建一个子项目**config-server**，**pom.xml**如下：
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-demo</artifactId>
        <groupId>com.iflytek</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
   
    <artifactId>config-server</artifactId>
    <packaging>jar</packaging>
   
    <name>config-server</name>
    <description>Demo project for Spring Cloud Config Server</description>
   
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
    </dependencies>
</project>
```
3.  创建启动类，**ConfigServerApplication.java**如下：
```JAVA
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class,args);
    }
}
```
4. 配置文件，**application.yml**如下：
```YML
server:
  port: 8888
spring:
  application:
    name: config-server
  cloud:
    config:
      label: master
      server:
        git:
          uri: https://github.com/huangdu94/down-to-earth.git
          search-paths: SpringCloud学习\code\respo
```
## 三、构建Config Client
1. 创建一个子项目**config-client**，**pom.xml**如下：
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-demo</artifactId>
        <groupId>com.iflytek</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>config-client</artifactId>
    <packaging>jar</packaging>

    <name>config-client</name>
    <description>Demo project for Spring Cloud Config Client</description>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
    </dependencies>
</project>
```
2.  创建启动类，**ConfigClientApplication**如下：
```JAVA
@SpringBootApplication
@RestController
public class ConfigClientApplication {
    @Value("${message}")
    private String message;
    @Value("${secret}")
    private String secret;
    
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
    
    @RequestMapping(value = "/show/{key}", method = RequestMethod.GET)
    public String getProperties(@PathVariable String key) {
        switch (key) {
            case "message":
                return message;
            case "secret":
                return secret;
            default:
                return String.format("key '%s' is not exist.", key);
        }
    }
}
```
4. 配置文件，**bootstrap.yml**如下：
```YML
server:
  port: 8081
spring:
  application:
    name: config-client
  cloud:
    config:
      label: master
      profile: dev
      uri: http://localhost:8888/
```
## 四、写程序中遇到的问题
1. 创建了一个**ConfigClientProperties**类用于读取配置，但是程序运行失败，最后发现Spring库中有同名类导致创建Bean时发生冲突。
2. 程序运行成功调试程序时，属性值读取异常，发现误将`@Value("${message}")`用成`@Value("message")`。

