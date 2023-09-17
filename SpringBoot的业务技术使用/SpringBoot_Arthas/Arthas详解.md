
## 使用 Arthas
### 一、Arthas 的简介
 - Arthas 是一款线上监控诊断产品，通过全局视角实时查看应用 Load、内存、GC、线程的状态信息，并能在不修改应用代码的情况下，对业务问题进行诊断，
 - 包括查看方法调用的出入参、异常，监测方法执行耗时，类加载信息等，大大提升线上问题排查效率。

### 二、Arthas 能解决什么问题？
   Arthas 是阿里巴巴出品的线上 JVM 监控诊断利器，它适用于：  
   1. 有没有一个全局 JVM 运行时监控？能够显示 CPU、线程、内存、堆栈等信息

   2. CPU 飙高是什么原因造成的？

   3. 接口没反应、卡住了，是不是死锁了？
   
   4. CTO 说你们这个接口太慢了，要优化一下，如何准确找出耗时的代码？
   
   5. 写的代码没有执行，是部署的分支不对，还是压根没提交？
   
   6. 线上有一个低级错误，改起来很简单，能不能在不重启应用的情况下，进行类替换，达到热部署？

### 三、Arthas快速入门

1. #### 进入Arthas 官网下载（https://arthas.aliyun.com/doc/install-detail.html）

2. #### 选择自己合适的下载方式(这里我是选择的手动安装)
   ![img](src/main/resources/static/img.png)

3. #### 在启动 Arthas 前，需要启动一个 Java 运行的JVM进程。

4. #### 启动Arthas的第一种方式   
   [1]、 终端输入 java -jar arthas-boot.jar 
        ![img_3](src/main/resources/static/img_3.png)
   [2]、 可以发现有四个可以监控的 Java 进程。如果直接回车，发现并不能监控到我们想要的 Java 进程
        ![img_4](src/main/resources/static/img_4.png)
   [3]、 所以在展示 Arthas的LOGO 之前输入想要的 Java进程序号，但是这样子会报错。原因在于:我们之前直接回车选择监控的Maven进程并没有结束也没有关闭
        ![img_5](src/main/resources/static/img_5.png)
   [4]、 因此，我们需要先进入Arthas中，执行 stop命令 关闭对Maven进程监控。
        ![img_6](src/main/resources/static/img_6.png)
   [5]、 重新输入启动命令，选择 Java进程序号
        ![img_7](src/main/resources/static/img_7.png)

5. #### 启动Arthas的第二种方式  
   [1]、 使用jps命令，查询 Java 进程的 pid。它可以显示Java虚拟机进程的执行主类名称、本地虚拟机唯一ID等信息。
        另外，jps命令只能显示它有访问权限的Java进程的信息。
        ![img_1](src/main/resources/static/img_1.png)
   [2]、 终端输入 java -jar arthas-boot.jar 进程pid （如果出现类似错误：也是使用 stop 命令）
        ![img_2](src/main/resources/static/img_2.png)

6. #### 输入 dashboard，查看 JVM 运行时监控
   ![img_8](src/main/resources/static/img_8.png)

7. #### 按 Ctrl + C 退出后。输入 thread 命令可以查看当前线程信息、线程的堆栈。
   ![img_9](src/main/resources/static/img_9.png)

8. #### 目标线程的序号是 17，运行下列命令查看 17 号线程对应的信息：
   ![img_10](src/main/resources/static/img_10.png)

9. #### jad是最简单的class反编译为java文件的小工具，使用 jad 命令进行反编译查看线上文件（避免中文乱码：java -Dfile.encoding=UTF-8 -jar arthas-boot.jar）
   ![img_11](src/main/resources/static/img_11.png)


### 四、Arthas进阶使用

1. #### 方法的监测  
   [1]、watch 命令用于监测方法执行数据 [不需要记忆，可以使用 IDEA 插件生成命令]
   ![img_13](src/main/resources/static/img_13.png)

   [2]、生成的命令就在剪切板里，直接粘贴到终端 [watch 全限定类名 方法名 检测的属性 监测5次 输出的对象属性遍历深度为3]
   ![img_12](src/main/resources/static/img_12.png)

   [3]、trace 命令用于获取方法内部调用路径，并输出方法路径上的每个节点上耗时。(耗时占比最高的部分会高亮显示)
   ![img_14](src/main/resources/static/img_14.png)

   [4]、stack 命令用于输出当前方法被调用的调用路径。
   ![img_15](src/main/resources/static/img_15.png)

   [5]、monitor 命令用于方法执行监控。
   ![img_16](src/main/resources/static/img_16.png)

2. #### 错误定位  
   [1]、

   [2]、

   [3]、

   [4]、

   [5]、


3. #### 时空隧道  
   [1]、

   [2]、

   [3]、

   [4]、

   [5]、


4. #### 生成火焰图
   [1]、profiler start

   [2]、profiler getSamples

   [3]、profiler status

   [4]、profiler stop

   [5]、