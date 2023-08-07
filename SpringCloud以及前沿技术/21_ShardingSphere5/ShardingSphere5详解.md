##  一、MySql 主从同步
 ### 1. 准备主服务器
- step1：在 docker 中创建并启动Mysql 主服务器：端口 3306
  ```
  docker run -d \
  -p 3306:3306 \
  -v /mysql/master/conf:/etc/mysql/conf.d \mysql配置文件映射到宿主机上
  -v /mysql/master/data:/var/lib/mysql \mysql数据映射到宿主机上
  -e MYSQL_ROOT_PASSWORD=131474 \
  --name mysql-master
  mysql:8.0.26
  ```
- step2:创建 Mysql 主服务器配置文件  
  &emsp;&emsp;默认情况下 Mysql 的 binlog 地址是自动开启的，可以通过如下配置定义一些可选配置
  ```
  vim /mysql/master/conf/my.cnf
  ```

  &emsp;&emsp;配置如下内容
  ```
  [mysqlId]
  # 服务器唯一 ID，默认值 1
  server-id=1
  # 设置日志格式，默认值 ROW
  # binlog_format=STATEMENT：日志记录的是主机数据库的写指令，性能高，但是 now() 之类的函数以及获取系统参数的操作会出现主从数据不同步的问题
  # binlog_format=ROW(默认)：日志记录的是主机数据库的写后的数据，批量操作是性能较差，解决 now() 或者 user() 或者 @@hostname 等操作在主从机器上不一致的问题
  binlog_format=STATEMENT
  
  # 二进制日志名，默认 binlog
  # log-bin=binlog
  # 设置需要复制的数据库，默认复制全部数据库
  # binlog-do-db=mytestdb
  # 设置不需要复制的数据库
  # binlog-ignore-db=mysql
  # binlog-ignore-db=infomation_schema
  ```

  &emsp;&emsp;重启 MySQL 容器  
  &emsp;&emsp;&emsp;&emsp;`docker restart mysql-master`

- step3：使用命令行登录 Mysql 主服务器
  ```
  # 进入容器：env LANG=C.UTF-8避免容器中显示中文乱码
  docker exec -it mysql-master env LANG=C.UTF-8 /bin/bash
  # 进入容器内的 mysql 命令行
  mysql -uroot -p
  # 修改默认密码校验方式（这里修改是使用的 Mysql 自带的可视化连接，可能会出现密码校验方式问题。一般使用自己的 navicat 没问题，所以我自己就没有执行这条一句进行修改）
  ALTER USER 'root'@'%' IDENTIFIED WIHT mysql_native_password BY '131474';
  ``` 

- setp4：主机中创建 slave 用户
  ```
  -- 创建 slave 用户,密码为 131474
  CREATE USER 'mysql_slave'@'%' IDENTIFIED BY '131474'; 
  -- 授予复制权限
  GRANT REPLICATION SLAVE ON *.* TO 'mysql_slave'@'%';
  -- 刷新权限
  FLUSH PRIVILEGES;
  ```
- step5：主机中查询 master 状态  
  &emsp;&emsp;执行完此步骤后不要在操作主服务器 MySQL，防止主服务器状态值变化  
  &emsp;&emsp;&emsp;&emsp;`SHOW MASTER STATUS;`  
  &emsp;&emsp;记下 File 和 Position 的值。执行完此步骤后不要再操作主服务器 Mysql，防止主服务器状态值变化。

---

### 2. 准备从服务器
