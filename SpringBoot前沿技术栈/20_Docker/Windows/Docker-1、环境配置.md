---

---

# **学习了解 Docker**

## 一、安装 虚拟机-VMware、CentOS 7镜像系统 [安装过程有部分缺省，忽略部分为默认配置直接下一步即可]

1. ### ***安装好VMware后，新建一个虚拟机,选择自定义配置***

   ![输入图片说明](../Windows/win-img/image1-1.png)

---

2. ### ***引入CentOS镜像：下载网址（[Index of /centos/7.9.2009/isos/x86_64/ | 清华大学开源软件镜像站 | Tsinghua Open Source Mirror](https://mirrors.tuna.tsinghua.edu.cn/centos/7.9.2009/isos/x86_64/)）***
   
   ![输入图片说明](../Windows/win-img/image1-2.png)

---

3. ### ***设置4个处理器***

   ![输入图片说明](../Windows/win-img/image1-3.png)

---

4. ### ***设置虚拟机内存：8G***

   ![输入图片说明](../Windows/win-img/image1-4.png)

---

5. ### ***设置网络连接类型***
   > 网络连接类型有以下几种，默认是NAT模式  
   >> 桥接模式：通过物理主机架设了一座桥，从而连入到了实际网络中。简单来说，在同一个局域网下，其他的主机可以访问其网络服务IP  
   >> NAT模式：通过宿主机上网和交换数据的。简单来说，一切上网方式都得通过宿主机，并且在同一局域网中无法被其他主机访问其端口服务

   ![输入图片说明](../Windows/win-img/image1-5.png)

---

6. ### ***设置磁盘大小：100G； 并将虚拟磁盘存储为单个文件； 完成创建虚拟机配置***

   ![输入图片说明](../Windows/win-img/image1-6.png)
   ![输入图片说明](../Windows/win-img/image1-7.png)
  
---

7. ### ***安装设置 中文 语言***

   ![输入图片说明](../Windows/win-img/image1-8.png)

---

8. ### ***设置安装地址，以及网络连接***

   ![输入图片说明](../Windows/win-img/image1-9.png)

   ![输入图片说明](../Windows/win-img/image1-10.png)

   ![输入图片说明](../Windows/win-img/image1-11.png)

---

9. ### ***开始安装***

   ![输入图片说明](../Windows/win-img/image1-12.png)

---

10. ### ***设置root密码(必须设置)； 以及自定用户名的信息(可不设置)***

    ![输入图片说明](../Windows/win-img/image1-13.png)

---

## 二、配置虚拟机环境

1. ### ***使用命令查看虚拟机是否安装成功***

   ```
   # 查看系统内核
   uname -r
   # 查看已安装的 CentOS 版本信息
   cat /etc/redhat-release
   ```

   ![输入图片说明](../Windows/win-img/image1-14.png)

---

2. ### ***查看虚拟机网络配置：VMnet0是否连接，是否配置成宿主网络***
    
   ![输入图片说明](../Windows/win-img/image1-15.png)
   ![输入图片说明](../Windows/win-img/image1-16.png)

---

3. ### ***验证虚拟机网络通信正常***

   ```
   ping baidu.com
   ```
   ![输入图片说明](../Windows/win-img/image1-17.png)

---

## 三、Xshell -- 终端模拟器

1. 查看虚拟机的 Ip 地址
   ``` 
   ip addr
   ```
   ![输入图片说明](../Windows/win-img/image1-23.png)

---

2. ### ***连接虚拟机***

   ![输入图片说明](../Windows/win-img/image1-18.png)
   
---

3. ### ***连接成功***

   ![输入图片说明](../Windows/win-img/image1-19.png)

---

4. ### ***配置虚拟机网络信息***
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
   # 新增，当前主机的IP：在起始和结束ip中选一个[如：192.168.2.128/24 指的是一个包含192.168.2.0 到 192.168.2.255 的子网内的所有IP地址]
   IPADDR=192.168.2.128
   # 网关----同宿主机的网关
   GATEWAY=192.168.2.1
   # 掩码
   NETMASK=255.255.255.0
   # DNS服务
   DNS1=8.8.8.8
   # DNS服务
   DNS2=8.8.4.4
   ```

## 四、系统初始化

1. ### ***关闭selinux（它是一个 Linux 内核模块，也是 Linux 的一个安全子系统)***

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

2. ### ***防火墙***

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

3. ### ***安装Net-tools (终端命令，没安装之前是无法执行 ifconfig 等命令的)***

   ```
   yum install net-tools -y
   ```

---

4. ### ***安装Openssh-server(便于直接使用命令进行连接虚拟机)***

   ```
   # 安装
   yum install openssh-server -y
   # 启动
   systemctl start sshd.service
   # 设置开机启动
   systemctl enable sshd.service
   ```
   ![输入图片说明](../Windows/win-img/image1-20.png)

---

5. ### ***修改Host，使IP映射域名***

   ![输入图片说明](../Windows/win-img/image1-21.png)
   ![输入图片说明](../Windows/win-img/image1-22.png)

---

