---

---

## **学习了解 Docker**

#### 一、安装 虚拟机-VMware、CentOS 镜像系统

> 1、安装好VMware后，新建一个虚拟机

![输入图片说明](src/main/resources/static/image1-1.png)
![71688276871_.pic](src/main/resources/static/71688276871_.pic.jpg)

---

> 2、引入CentOS镜像：下载网址（[Index of /centos/7.9.2009/isos/x86_64/ | 清华大学开源软件镜像站 | Tsinghua Open Source Mirror](https://mirrors.tuna.tsinghua.edu.cn/centos/7.9.2009/isos/x86_64/)）

![输入图片说明](src/main/resources/static/image1-2.png)
![81688276903_.pic](src/main/resources/static/81688276903_.pic.jpg)

---

> 3、设置4个处理器

![输入图片说明](src/main/resources/static/image1-3.png)
![91688277153_.pic](src/main/resources/static/91688277153_.pic.jpg)

---

> 4、设置虚拟机内存：8G

![输入图片说明](src/main/resources/static/image1-4.png)
![101688277186_.pic](src/main/resources/static/101688277186_.pic.jpg)

---
![输入图片说明](src/main/resources/static/image1-5.png)
> 5、设置磁盘大小：100G

![输入图片说明](src/main/resources/static/image1-6.png)
![111688277206_.pic](src/main/resources/static/111688277206_.pic.jpg)

---
![输入图片说明](src/main/resources/static/image1-7.png)

> 6、安装设置语言

![输入图片说明](src/main/resources/static/image1-8.png)
![121688277425_.pic](src/main/resources/static/121688277425_.pic.png)

---

> 7、设置安装地址

![输入图片说明](src/main/resources/static/image1-9.png)
![131688277454_.pic](src/main/resources/static/131688277454_.pic.png)

![输入图片说明](src/main/resources/static/image1-10.png)
![141688277487_.pic](src/main/resources/static/141688277487_.pic.png)

![输入图片说明](src/main/resources/static/image1-11.png)
![输入图片说明](src/main/resources/static/image1-12.png)
---

> 8、开始安装

![151688277505_.pic](src/main/resources/static/151688277505_.pic.png)

---

> 9、设置root密码，以及自定用户名的信息

![输入图片说明](src/main/resources/static/image1-13.png)
![161688277592_.pic](src/main/resources/static/161688277592_.pic.png)

---

> 10、使用命令查看是否安装成功

```
uname -r
cat /etc/redhat-release
```

![输入图片说明](src/main/resources/static/image1-14.png)
![WechatIMG21](src/main/resources/static/WechatIMG21.png)

#### 二、配置虚拟机环境

> 1、查看虚拟机网络配置：VMnet8是否连接，是否启动，IP、网关地址，掩码，起始IP、结束IP等

![171688279618_.pic](src/main/resources/static/171688279618_.pic.png)

![181688279644_.pic](src/main/resources/static/181688279644_.pic.jpg)

---

> 2、配置虚拟机网络信息

``` 
# 终端命令
cd /etc/sysconfig/network-scripts/
ls
vi ifcfg-ens33
```

```
# 进入配置文件中
TYPE=Ethernet
PROXY_METHOD=none
BROWSER_ONLY=no
# 由原来的dhcp改成static
BOOTPROTO=static
DEFROUTE=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_FAILURE_FATAL=no
IPV6_ADDR_GEN_MODE=stable-privacy
NAME=ens33
UUID=cde0adc2-1f90-4f9a-ae7a-f3039908c103
DEVICE=ens33
# 由原来的no改成yes
ONBOOT=yes
# 新增，当前主机的IP：在起始和结束ip中选一个
IPADDR=192.168.75.128
# 网关
GATEWAY=192.168.75.2
# 掩码
NETMASK=255.255.255.0
# DNS服务
DNS1=8.8.8.8
# DNS服务
DNS2=8.8.4.4
```

> 3、验证虚拟机网络通信正常

```
ping baidu.com
```

![221688291085_.pic](src/main/resources/static/221688291085_.pic.jpg)

#### 三、Xshell -- 终端模拟器

> 1、连接虚拟机

![231688291267_.pic](src/main/resources/static/231688291267_.pic.png)

---

> 2、连接成功

![241688291354_.pic](src/main/resources/static/241688291354_.pic.jpg)

---

#### 四、系统初始化

- 关闭selinux（它是一个 Linux 内核模块，也是 Linux 的一个安全子系统)

    - 查看状态

      ```
      getenforce
      ```

    - 临时关闭

      ```
      setenforce 0
      ```

    - 永久关闭

      ```
      vi /etc/selinux/config
      ```

      ```
      # 设置为disabled
      SELINUX=disabled
      ```

---

- 防火墙

    - 查看防火墙状态

      ```
      systemctl status firewalld
      ```

    - 关闭

      ```
      systemctl stop firewalld
      ```

    - 关闭开机启动防火墙

      ```
      systemctl disable firewalld
      ```

---

- 安装Net-tools (终端命令，没安装之前是无法执行 ifconfig 等命令的)

  ```
  yum install net-tools -y
  ```

---

- 安装Openssh-server(便于直接使用命令进行连接虚拟机)

  ```
  # 安装
  yum install openssh-server -y
  # 启动
  systemctl start sshd.service
  # 设置开机启动
  systemctl enable sshd.service
  ```

  ![261688301191_.pic](src/main/resources/static/261688301191_.pic.png)

---

- 修改Host，使IP映射域名

  ![281688301260_.pic](src/main/resources/static/281688301260_.pic.jpg)

  ![271688301224_.pic](src/main/resources/static/271688301224_.pic.png)

---

- 可以在win上用Xshell等工具SSH连接CentOS

- wget（下载工具）

  ```
  yum install wget -y
  ```

---

- CentOS常用工具包

  ```
  yum install -y wget bash-completion vim lrzsz wget expect net-tools nc nmap tree dos2unix htop iftop iotop unzip telnet sl psmisc nethogs glances bc ntpdate openldap-devel
  ```

---

