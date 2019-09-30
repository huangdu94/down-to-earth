# Eureka
​		原文作者为CSDN博主[方志朋](https://blog.csdn.net/forezp)，遵循 [CC 4.0 BY-SA](https://creativecommons.org/licenses/by-sa/4.0/) 版权协议，转载请附上原文出处链接及本声明。[原文链接](https://blog.csdn.net/forezp/article/details/81041028)  
​		**本笔记在此基础上修改整理。**
## 一、简介
​		Eureka可以作为服务注册与发现的组件。
## 二、构建Eureka Server
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
        <module>eureka-server</module>
        <module>eureka-client</module>
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
2. 创建一个子项目**erueka-server**，**pom.xml**如下：
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

    <artifactId>eureka-server</artifactId>
    <packaging>jar</packaging>

    <name>eureka-server</name>
    <description>Demo project for Eureka Server</description>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
    </dependencies>
</project>
```
3.  创建启动类，**EurekaServerApplication.java**如下：
```JAVA
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```
4. 配置文件，**application.yml**如下：
```YML
server:
  port: 8761
spring:
  application:
    name: eurka-server
eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false #是否向eureka注册 本身是eureka不用注册
    fetchRegistry: false #是否向缓存中注册 eureka不需要注册
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
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
2.  创建启动类，**EurekaClientApplication**如下：
```JAVA
@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication {
    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @RequestMapping("/{name}")
    public String home(@PathVariable String name, @RequestParam(value = "message", defaultValue = "Hello World") String message) {
        return String.format("Come by port: %s,say to %s: %s.", port, name, message);
    }
}
```
3. 配置文件，**application.yml**如下：
```YML
server:
  port: 8762
spring:
  application:
    name: eureka-client #服务与服务之间相互调用一般都是根据这个name
eureka:
  instance:
    prefer-ip-address: true #注册服务的IP,而不是服务器名称
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```