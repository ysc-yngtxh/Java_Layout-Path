
配置了国内镜像源(比如阿里的docker镜像源)是对search没有用的,因为docker命令最后还是通过他自己的地址搜索镜像.
但是配置了国内镜像源可以加快pull拉去镜像的速度

当镜像为 latest 时，通过命令：docker image inspect [镜像名称]:latest | grep -i version


SpringBoot项目Docker打包部署

关于SpringBoot项目的搭建，之前的文章已经做过介绍，这里默认已经具备完整的SpringBoot的项目，且使用到了redis和mysql，并且能够在本地环境通过java -jar命令启动。

1.Docker查看容器的ip地址

查看ip地址的目的在于，通常我们会在SpringBoot项目的yml配置文件中配置redis、mysql等参数，因此需要获取到它们在容器内的ip地址。

输入：docker inspect xxx| grep IPAddress

xxx为redis或mysql的容器名称。

2.springboot项目docker打包部署

（1）pom添加docker依赖。

（2）通过idea的maven工具打出jar包或war包。

（3）制作Dockerfile

在项目目录下创建Dockerfile文件，文件内容为：

FROM openjdk:8
EXPOSE 8081
VOLUME /tmp
ADD springbootdocker-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

（4）把打好的jar包移动到和Dockerfile同级目录

（5）制作镜像

下载jdk镜像：docker pull openjdk:8

编译：docker build -f Dockerfile -t docker-springboot .

执行完毕后直接就在docker images中看到app这个SpringBoot的镜像。

（5）创建容器并启动

docker run --name app -p 8081:8081 -d app:2.0，也可以在Docker Desktop客户端启动。



查看容器当中的域名：cat /etc/hosts


docker network ls --filter driver=bridge --format "{{.ID}}" | xargs docker network inspect --format "route {{range .IPAM.Config}}{{.Subnet}}{{end}}" >> /usr/local/etc/docker-connector.conf
