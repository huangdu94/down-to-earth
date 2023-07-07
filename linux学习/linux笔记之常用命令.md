## 常用快捷键
+ `Ctrl+C`终止当前命令
+ `Ctrl+D`退出当前终端
+ `Ctrl+Z`暂停当前进程
+ `Ctrl+L`清屏
+ `Ctrl+S`暂停该终端，阻止向该终端输出
+ `Ctrl+Q`恢复向终端输出
+ `Tab`补全命令
## bash手册
+ `man`查看手册
+ 其它命令`info` `help`
## 一、文件与目录管理
|目录|用途|
|:-|:-|
|/|虚拟目录的根目录。通常不会在这里存储文件|
|/bin| (binaries) 二进制目录，存放许多用户级的GNU工具|
|/boot|启动目录，存放启动文件|
|/dev|设备目录，Linux在这里创建设备节点|
|/etc| (etcetera) 系统配置文件目录|
|/home|主目录，Linux在这里创建用户目录|
|/lib|库目录，存放系统和应用程序的库文件|
|/media|媒体目录，可移动媒体设备的常用挂载点|
|/mnt|挂载目录，另一个可移动媒体设备的常用挂载点|
|/opt|可选目录，常用于存放第三方软件包和数据文件|
|/proc|进程目录，存放现有硬件及当前进程的相关信息|
|/root|root用户的主目录|
|/sbin| (super user binaries)系统二进制目录，存放许多GNU管理员级工具|
|/run|运行目录，存放系统运作时的运行时数据|
|/srv|服务目录，存放本地服务的相关文件|
|/sys|系统目录，存放系统硬件信息的相关文件|
|/tmp|临时目录，可以在该目录中创建和删除临时工作文件|
|/usr| (unix shared resources) 用户二进制目录，大量用户级的GNU工具和数据文件都存储在这里|
|/var|可变目录，用以存放经常变化的文件，比如日志文件|
### 遍历目录
+ 相对路径，绝对路径
+ `.`当前目录
+ `..`当前目录的父目录
+ `cd`进入到某一个目录
+ `pwd`查看当前所在目录
ls	查看某个目录或者某个文件	-a列出隐藏的 -l详细信息 --color -d只显示目录
mkdir	创建一个目录	-m制定要创建目录的权限 -p创一串目录
rmdir	删除一个目录	-p删除一串目录
rm	删除目录或者文件	-f删除不存在的文件不会报错 -r删除一串	-rf常用组合

which 用来查找一个命令的绝对路径（下面详细）
alias 用来设置指令的别名	alias[别名]=[指令名称]
（使用绝对路径的命令则不会被alias）
unalias	解除别名

如何正确关机
`who netstat -a ps -aux shutdown`
系统的远程登录 `ssh` windows常用软件 `Putty`
环境变量
echo	打印	>重定向 >>追加
cp	复制	-d 快捷方式 -r拷贝目录 -i询问是否覆盖 -u源文件比目标文件新才会拷贝
touch	没有文件创建，有的话改变文件的访问时间
cat	读一个文件，并把读出的内容打印到当前屏幕上 -n显示行号 -A显示所有东西出来 tac反向打印文件内容
more/less 查看文件内容（比较多的时候）
head	显示文件前十行 -n显示行数	tail 显示文件后十行 -n	-f动态显示
mv	移动	

文件的所属主以及所属组 linux文件属性
ls -l	查看当前目录下的文件时，共显示了9列内容
第1列10位 第1位文件类型 -普通文件 d目录 l连接文件 c串行端口设备 s套接字文件
后9位 3位为一组 分别代表所有主、所在组、其他用户对该文件的权限 rwx分别为可读、可写、可执行

chgrp 组名 文件名 修改文件所在组
chown [-R] 用户名[:组名] 文件名 -R用于目录，级联更改 更改文件所在主/组
useradd 增加一个账户
chmod [-R] xyz 文件名 其中xyz遵循r4w2x1
或者 u/g/o +/-/= r/w/x
umask 数值代表的含义为，上边两条规则中的默认值（文件为666，目录为777）需要减掉的权限。可以在/etc/bashrc里面修改
chattr 修改文件的特殊属性
lsattr 列出文件/目录的特殊属性

在linux下搜索一个文件
which 用来查找一个命令的绝对路径，只能用来查找PATH环境变量中出现的路径下的可执行文件
whereis locate 通过查找预先生成的文件列表库来告诉用户要查找的文件在哪里
find [路径][参数]
-atime读取或执行文件时更改 -ctime更改权限...时更改 -mtime写入文件时随文件内容修改更改 +n 大于n天
ls -lu -lc -l 分别列出文件的 atime/ctime/mtime
-name filename	直接查找该文件名的文件
-type type	通过文件类型查找

ln [-s] [来源文件][目的文件] -s如果不加就是建立硬连接，加上就建立软连接

### 认识/etc/passwd和/etc/shadow
+ /etc/passwd (7个字段)
  1. 用户名
  2. 账号口令(x)
  3. uid用户标识号
  4. gid组标识号
  5. findger注释说明
  6. 用户home
  7. shell
+ /etc/shadow(9个字段)
  1. 用户名
  2. 用户密码
  3. 上次更改密码的日期
  4. 要过多少天才能改密码
  5. 密码多少天后到期
  6. 密码警告期限
  7. 账号失效期_密码失效后
  8. 账号的生命周期
  9. 10.保留

groupadd groupdel useradd userdel 新增/删除组 新增/删除用户
chfn 更改用户的finger
passwd[username] 修改用户密码（默认修改当前用户的密码）
groups[username] 查看自己属于哪个用户组
usermod 可用来修改用户账号的各项设定

用户身份切换
su [-] username 加-切换用户后跳到该用户的家目录
echo $LOGNAME	查看当前登录的用户名
sudo	执行一个root才能执行的命令 别的用户使用sudo 需要visudo命令编辑 /etc/sudoers
———————————————————————————————————————————————————————————
磁盘管理
df	查看已挂载磁盘的总容量、使用容量、剩余容量 默认以k为单位显示
-i 使用inodes显示结果 -h使用合适的单位显示 -k-m分别为使用K，M为单位显示
du	用来查看某个目录所占空间大小
-a 全部文件与目录大小都列出来 -b-k-m-h -c最后加总 -s只列出总和

磁盘的分区和格式化
fdisk [-l][设备名称] 
-l:后边不跟设备名会直接列出系统中所有的磁盘设备以及分区表，加上设备名会列出该设备的分区表。
如果不加-l则进入另一个模式，在该模式下，可以对磁盘进行分区操作。
......
———————————————————————————————————————————————————————————
vi不会显示颜色 vim会显示颜色

vim的三种模式：一般模式、编辑模式、命令模式。
一般模式：上下移动光标；删除某个字符；删除某行；复制、粘贴一行或者多行。
编辑模式：一般模式下，是不可以修改某一个字符的，只能到编辑模式。ESC
命令模式：搜索某个字符或者字符串，也可以保存、替换、退出、显示行号等等。
———————————————————————————————————————————————————————————
Linux环境中 打包压缩文件的扩展名: tar tar.gz gz bz2 tar.xz 统称为tar包
压缩比 gz<bz2<xz	压缩速度 gz>bz2>xz
对于Linux系统来说文件扩展名没有任何卵用 但是便于识别理解

打包：实质将许多文件和目录打包成一个文件
压缩：把大文件或目录通过压缩算法变成一个较小的文件

tar:打包、压缩一个命令全搞定.版本稍高的tar命令,选项前的-,是可选的.解压缩"tar"包非常简单,只需要这样：

tar -cvf filename.tar 源文件
tar -xvf filename [-C 目录]

指定压缩算法：z代表gz,j代表bz2,J代表xz.
tar -zcvf filename.tar.gz 	源文件
tar -jcvf filename.tar.bz2 	源文件
tar -Jcvf filename.tar.xz 	源文件
查看需要解压的文件名
tar -ztvf filename.tar.gz
tar -jtvf filename.tar.bz2
tar -Jtvf filename.tar.xz

tar -zxvf filename.tar.gz	目录	
tar -jxvf filename.tar.bz2	目录
tar -Jxvf filename.tar.xz	目录
使用 -C指定绝对路径

gzip	-c将压缩输出到stdout -d解压缩 -v详细信息 -#压缩比1-9
bzip2	同上 -k保留原文件
gzip和bzip2只对单个文件压缩

zip 目标文件 源文件
unzip 文件 -d目录
————————————————————————————————————————————————————————————
安装	如何在linux系统下安装一个软件

RPM	"Redhat Package Manager" Redhat公司开发出来的

rpm -i安装 -v可视化 -h显示安装进度 filename --force覆盖也要安装 --nodeps没有也要安装	安装一个rpm包
rpm -Uvh filename 	升级一个rpm包
rpm -qa | grep file	-q查询 -a查询所有安装包	（查询一个包是否安装，不带有平台信息以及后缀名的）
	grep	用来过滤某个关键词的工具
	|	管道符，用来把前面运行的结果传递给后面的命令
rpm -e filename	卸载一个rpm包
rpm -ql 包名	列出一个rpm包安装的文件
rpm -qf 文件的绝对路径	列出某一个文件属于哪个rpm包
`命令`	引用反引号内命令执行的结果

yum list 列出所有可用的rpm包
yum search [相关关键词]	搜索一个rpm包
yum install [-y] [rpm 包名] -y就不会有太多询问信息
yum remove [-y] [rpm 包名]
yum update [-y] [rpm 包名]
使用本地光盘制作一个yum源

安装源码包 1.下载一个源码包 2.解压源码包 3.配置相关的选项 4.进行编译 5.安装
wget 下载命令 约定最好把所有下载的源码包放到/usr/local/src/目录下 源码包安装在/usr/local/目录下
echo $? 判断上一步有没有执行成功，如果是0则表示成功
./config --help 可以查看可用的选项
回车后执行check操作，check完成后生成了Makefile文件
make 进行编译
make install 会创建相应的目录文件
————————————————————————————————————————————————————————————

Linux命令：rz上传 sz下载