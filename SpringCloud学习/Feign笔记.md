# Feign
​		原文作者为CSDN博主[方志朋](https://blog.csdn.net/forezp)，遵循 [CC 4.0 BY-SA](https://creativecommons.org/licenses/by-sa/4.0/) 版权协议，转载请附上原文出处链接及本声明。[原文链接](https://blog.csdn.net/forezp/article/details/81041028)  
​		**本笔记在此基础上修改整理。**

## 一、简介
+ Feign 采用的是基于接口的注解
+ Feign 整合了ribbon，具有**负载均衡**的能力
+ 整合了Hystrix，具有**熔断**的能力
## 二、准备工作
​		参考**Eureka笔记**，创建**eureka-server**和**eureka-client**，启动一个**eureka-server**和两个**eureka-client**，用于演示。
## 三、建一个服务消费者
1. 创建一个父项目**spring-cloud-demo**，**pom.xml**如下:
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.iflytek</groupId>
    <artifactId>spring-cloud-demo</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <name>spring-cloud-demo</name>
    <description>Demo project for Spring Cloud</description>

    <modules>
        <module>service-feign</module>
    </modules>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
        <spring-cloud.version>Finchley.RELEASE</spring-cloud.version>
    </properties>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.3.RELEASE</version>
        <relativePath/>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```
2. 创建一个子项目**service-feign**，**pom.xml**如下：
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

    <artifactId>service-feign</artifactId>
    <packaging>jar</packaging>

    <name>service-feign</name>
    <description>Demo project for Feign</description>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
    </dependencies>
</project>
```
3.  创建启动类，**FeignApplication.java**如下：
```JAVA
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }
}
```
4. 配置文件，**application.yml**如下：
```YML
server:
  port: 8765
spring:
  application:
    name: service-feign
eureka:
  instance:
    prefer-ip-address: true
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```
5. **CallRemoteController**、**CallRemoteService**如下：
```JAVA
@RestController
public class CallRemoteController {
    @Autowired
    private CallRemoteService callRemoteService;

    @RequestMapping("/callRemote")
    public String callRemoteService() {
        return callRemoteService.sayHelloCallByRemote("duhuang","Hi,boy!");
    }
}
```
```JAVA
@FeignClient("eureka-client")
public interface CallRemoteService {
    //@FeignClient("spring.application.name")接下来接口中定义的方法@RequestMapping和参数列表要和远程服务中的一致(方法名可以不同)
    @RequestMapping("/{name}")
    String sayHelloCallByRemote(@PathVariable String name, @RequestParam(value = "message", defaultValue = "Hello World") String message);
}
```