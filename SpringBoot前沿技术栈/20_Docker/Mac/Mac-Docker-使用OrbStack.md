
## OrbStack 官方地址：https://orbstack.dev/

   ### MacOS上的Docker Desktop原本就是饱受诟病,慢、重、资源消耗巨大。OrbStack的出现就是为了解决这个问题。
   ### 安装OrbStack：brew install orbstack

   <h3>
     1、简介
     <figure>
       OrbStack是一款零配置就能够轻松进行使用的虚拟机容器软件，能够免费的提供给个人使用，相对于Mac上面的其他虚拟机来说更为的简单,
       对于喜欢在Mac上面运行Linux个人来说更为的实用。
     </figure>
   </h3>

   <h3>
     2、配置国内 docker 加速镜像源
     <figure>
       自 2024年 6 月份，国内的 docker 镜像地址都失效后，许多的开源地址都无法正常使用。
       <br/>
       截止目前 24年 10月，配置国内镜像源(比如阿里的docker镜像源)是对 'docker search [镜像名称]' 命令没有用的，
       因为docker的 search 命令最后还是通过他自己的地址搜索官方 Docker Hub 镜像，导致请求超时，无法正常响应。
       <br/>
       但是配置了国内镜像源可以去加快 pull 拉取镜像的速度。（只要不影响拉取镜像，个人觉得问题不大）
     </figure>
   </h3>

## OrbStack 的使用步骤

SpringBoot项目Docker打包部署

1.Docker查看容器的ip地址

查看ip地址的目的在于，通常我们会在SpringBoot项目的yml配置文件中配置redis、mysql等参数，因此需要获取到它们在容器内的ip地址。

方法一：docker inspect [容器名称] | grep IPAddress
方法二：docker exec -it [容器名称] /bin/bash
       cat /etc/hosts

当镜像为 latest 时，通过命令：docker image inspect [镜像名称]:latest | grep -i version


2.springboot项目docker打包部署

（1）pom添加docker依赖。

（2）通过idea的maven工具打出jar包或war包。

（3）制作Dockerfile

在项目目录下创建Dockerfile文件，文件内容为：

FROM openjdk:8
EXPOSE 8081
VOLUME /tmp
ADD docker-springboot.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

（4）把打好的jar包移动到和Dockerfile同级目录

（5）制作镜像

下载jdk镜像：docker pull openjdk:23

编译：docker build -f Dockerfile -t docker-springboot .

执行完毕后直接就在 docker images 中看到这个SpringBoot的镜像。

（5）创建容器并启动

docker run --name docker-springboot -p 8080:8080 -d docker-springboot，也可以在Docker Desktop客户端启动。



## MacOS无法连接docker容器解决方案
   1. 首先 Mac 端通过 brew 安装 docker-connector
   ```dockerignore
      brew install wenjunxiao/brew/docker-connector
   ```

   2. 然后执行以下命令把 docker 的所有 bridge 网络都添加到路由中
   ```dockerignore
      注意：/tmp/docker-connector.conf 是宿主机的可自定义路径地址
      docker network ls --filter driver=bridge --format "{{.ID}}" | xargs docker network inspect --format "route {{range .IPAM.Config}}{{.Subnet}}{{end}}" >> /tmp/docker-connector.conf
   ```
   
   3. 配置完成，直接启动服务（需要 sudo，路由配置启动之后仍然可以修改，并且无需重启服务立即生效）
   ```dockerignore
      sudo brew services start docker-connector
   ```
   4. 然后使用以下命令在 docker 端运行 wenjunxiao/mac-docker-connector，需要使用 host 网络，并且允许 NET_ADMIN
   ```dockerignore
      docker run -it -d --restart always --net host --cap-add NET_ADMIN --name connector wenjunxiao/mac-docker-connector
   ```


docker-compose 的诸多优点：brew install docker-compose
在单个主机上建立多个隔离环境，Compose 使用项目名称将环境彼此隔离。您可以在多个不同的上下文中使用此项目名称。默认项目名称是项目目录的基本名称。您可以使用-p 命令行选项或 COMPOSE_PROJECT_NAME 环境变量设置自定义项目名称 。默认项目目录是 Compose 文件的基本目录。可以使用--project-directory 命令行选项自定义项目目录。
创建容器时保留卷数据
仅重新创建已更改的容器，当您重新启动未更改的服务时，Compose 会使用现有容器。
变量在环境之间组合重复使用


docker network create --subnet=x.x.x.0/24 netBridgeName  创建自定义网桥
运行容器并指定IP地址 docker run -itd --network=netBridgeName --ip x.x.x.8 --name dockerName imageName