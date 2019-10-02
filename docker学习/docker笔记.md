# docker笔记
## docker安装
1. win10家庭版装docker	[原文链接](https://www.jianshu.com/p/a24c5974067f)
+ 新建cmd文件，内容如下，以管理员身份运行。  
```CMD
pushd "%~dp0"
dir /b %SystemRoot%\servicing\Packages\*Hyper-V*.mum >hyper-v.txt
for /f %%i in ('findstr /i . hyper-v.txt 2^>nul') do dism /online 	/norestart /add-package:"%SystemRoot%\servicing\Packages\%%i"
del hyper-v.txt
Dism /online /enable-feature /featurename:Microsoft-Hyper-V-All 	/LimitAccess /ALL
```
+ 修改注册表`计算机\HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion`路径下`EditionID`值为`Professional`。
+ [docker官网](https://www.docker.com/get-started)点击`Download Desktop and Take a Tutorial`下载安装。
2. 其它系统安装	[菜鸟教程](https://www.runoob.com/docker/docker-tutorial.html)
## docker使用
本笔记参照：[菜鸟教程](https://www.runoob.com/docker/docker-tutorial.html)
### Docker Hello World
1. Hello World  
    `docker run ubuntu:15.10 /bin/echo "Hello world"`
2. 运行交互式的容器  
    `docker run -i -t ubuntu:15.10 /bin/bash`
3. 启动容器(后台模式)
   + 创建一个以进程方式运行的容器  
   `docker run -d ubuntu:15.10 /bin/sh -c "while true; do echo hello world; sleep 1; done"`
   + 查看运行中的容器	`docker ps`
   + 查看容器的标准输出	`docker logs [ID|NAMES]`
   + 停止容器	`docker stop [ID|NAMES]`
### Docker 容器使用
1. Docker客户端
   + 查看Docker客户端的所有命令选项	`docker`
   + 查看指定命令的使用方法	`docker [command] --help`
2. Web应用
   + 载入Web应用镜像	`docker pull training/webapp`
   + 运行应用(-P 容器内部端口随机映射到主机的高端口)  
   `docker run -d -P training/webapp python app.py` 
   + 查看Web应用容器(加-l参数查询最后创建的)	`docker ps`
   + 设置指定端口(-p)  
     `docker run -d -p 5000:5000 training/webapp python app.py`
   + 查看网络端口的快捷方式	`docker port [ID|NAMES]`
   + 查看日志(和上面的有所区别加了-f参数)	`docker logs -f [ID|NAMES]`
   + 查看容器运行的进程	`docker top [ID|NAMES]`
   + 检查Web应用	`docker inspect [ID|NAMES]`
   + 停止、重启、移出	`docker stop|start|restart|rm [ID|NAMES]`
### Docker 镜像使用
1. 列出镜像列表	`docker images`
2. 查找镜像
   + [Docker Hub](https://hub.docker.com/)
   + `docker search [repname]`
3. 获取一个新的镜像	`docker pull [repname]`
4. 更新镜像(先用镜像创建容器，在运行的容器中使用)	`apt-get update`
   + 提交到本地  
   `docker commit -m="message" -a="username" [ID] [username]/[repname]:[tag]`
   + 提交到Docker Hub远程仓库
   `docker push [ID]`
5. 设置镜像标签
   `docker tag [ID] [username]/[repname]:[tag]`
6. 构建镜像
   `docker build -t [username]/[repname]:[tag] [Dockerfile path]`
   Dockerfile文件实例：
   ```SH
   FROM    centos:6.7
   MAINTAINER      Fisher "fisher@sudops.com"
   RUN     /bin/echo 'root:123456' |chpasswd
   RUN     useradd runoob
   RUN     /bin/echo 'runoob:123456' |chpasswd
   RUN     /bin/echo -e "LANG=\"en_US.UTF-8\"" >/etc/default/local
   EXPOSE  22
   EXPOSE  80
   CMD     /usr/sbin/sshd -D
   ```
### Docker 容器连接
1. 网络端口映射
   + 随机映射端口  
   `docker run -d -P training/webapp python app.py`
   + 映射指定端口  
   `docker run -d -p 5000:5000 training/webapp python app.py`
   + 指定容器绑定的IP  
   `docker run -d -p 127.0.0.1:5001:5000 training/webapp python app.py`
   + 绑定UDP端口(默认TCP)  
   `docker run -d -p 127.0.0.1:5000:5000/udp training/webapp python app.py`
2. Docker容器连接
   + 端口映射并不是唯一把 docker 连接到另一个容器的方法。
   + docker有一个连接系统允许将多个容器连接在一起，共享连接信息。
   + docker连接会创建一个父子关系，其中父容器可以看到子容器的信息。
3. 容器命名  
`docker run -d -P --name runoob training/webapp python app.py`