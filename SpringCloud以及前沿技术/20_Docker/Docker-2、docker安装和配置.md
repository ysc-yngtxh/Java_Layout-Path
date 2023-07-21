## 一、安装docker-ce社区版

- 配置repo源

  ``` 
  curl -o /etc/yum.repos.d/Centos-7.repo http://mirrors.aliyun.com/repo/Centos-7.repo
  curl -o /etc/yum.repos.d/docker-ce.repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
  
  yum clean all && yum makecache
  ```

- 查看可下载版本

  ```
  yum list docker-ce --showduplicates | sort -r
  ```

- 安装

  ```
  # 最新版
  yum install -y docker-ce
  # 指定版本
  yum install -y docker-ce-23.0.6
  ```

## 二、启动docker-ce社区版

- 设置开机启动

  ```
  systemctl enable docker
  ```

- 启动docker

  ```
  # 启动
  systemctl start docker
  # 重启
  systemctl restart docker
  ```

- 停止docker

  ```
  systemctl stop docker
  ```

- 其他

  ```
  # 查看docker版本
  docker version
  # 查看docker信息
  docker info
  # docker-client
  
  ```

  