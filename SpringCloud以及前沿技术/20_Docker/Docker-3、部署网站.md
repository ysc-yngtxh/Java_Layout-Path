查看当前下载的jdk版本
https://www.oracle.com/java/technologies/downloads/#jdk17-linux
通过wget命令下载jdk17
wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.tar.gz
下载完需要我们通过配置Dockerfile来制作镜像
vim Dockerfile

# 指定基础镜像 ubuntu:23.04
FROM ubuntu:23.04

# 设置环境变量
ENV JAVA_HOME=/usr/local/jdk-17.0.8
ENV JRE_HOME=$JAVA_HOME/jre
ENV PATH=${JAVA_HOME}/bin:$PATH

# 复制当前路径下的jdk-17_linux-x64_bin.tar.gz文件或目录到容器的指定路径/usr/local/
ADD jdk-17_linux-x64_bin.tar.gz /usr/local/
# 运行指定的命令
RUN javac --version \
    && java --version



构建我们的服务器Java镜像
docker build -t java:17.0.8 .
# 镜像名：java；版本：17.0.8；需要注意的是后面一定要空格之后有一个.符号

docker images
!图片


