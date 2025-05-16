
/*
 * 一部分(maven介绍及安装配置)
 *     一、完成一个Java项目，需要做哪些工作？
 *         1、分析项目要做什么，知道项目有哪些组成部分。
 *         2、涉及项目，通过哪些步骤，使用哪些技术。需要多少人，多长时间。
 *         3、组建团队，招人，购置设备(服务器，软件，笔记本......)
 *         4、开发人员写代码。开发人员需要测试自己写代码。重复多次的工作。
 *         5、测试人员，测试项目功能是否符合要求。
 *            测试开发人员提交代码--如果测试有问题--需要开发人员修改--再提交代码给测试--
 *            测试人员再测试代码--如果还有问题--再交给开发人员--开发人员再提交--再测试，直到测试代码通过
 *
 *     二、传统开发项目的问题，没有使用maven管理的项目
 *         1、很多模块，模块之间有关系，手工管理关系，比较繁琐。
 *         2、需要很多第三方功能，需要很多jar文件，需要手工从网络中获取各个jar
 *         3、需要管理jar的版本，你需要的是mysql1.5.1.5.jar 那你不能给一个mysql1.4.0.jar
 *         4、管理jar文件之间的依赖，你的项目要使用a.jar 需要使用b.jar里面的类。
 *            必须首先获取到b.jar才可以，然后才能使用a.jar
 *
 *            a.jar需要b.jar这个关系叫做依赖，或者你的项目中要使用MySQL的驱动，也可以叫做项目依赖MySQL驱动。
 *            a.class使用b.class，a依赖b类
 *
 *     三、需要改进项目的开发和管理，需要maven
 *         1、maven可以管理jar文件
 *         2、自动下载jar和他的文档，源代码
 *         3、管理jar直接的依赖， a.jar需要b.jar， maven会自动下载b.jar
 *         4、管理你需要的jar版本
 *         5、帮你编译程序，把java编译为class
 *         6、帮你测试你的代码是否正确
 *         7、帮你打包文件，形成jar文件，或者war文件
 *         8、帮你部署项目
 *
 *     四、构建：项目的构建
 *        构建是面向过程的，就是一些步骤，完成项目代码的编译，测试，运行，打包，部署等等。
 *        maven支持的构建包括有：
 *         1、清理，把之前项目编译的东西删除掉，为新的编译代码做准备。
 *         2、编译，把程序源代码编译为执行代码，java-class文件
 *                 批量的，maven可以同时把成百上千的文件编译为class。
 *                 javac不一样，javac一次编译一个文件。
 *         3、测试，maven可以执行测试程序代码，验证你的功能是否正确。
 *                 批量的，maven同时执行多个测试代码，同时测试很多功能
 *         4、报告，生成测试结果的文件，测试通过没有
 *         5、打包，把你的项目中所有的class文件，配置文件等所有资源放到一个压缩文件中。
 *                 这个压缩文件就是项目的结果文件。通常Java程序，压缩文件是jar扩展名的。
 *                 对于web应用，压缩文件扩展名是.war
 *         6、安装，把5中生成的文件jar，war安装到本机仓库。
 *         7、部署，把程序安装好可以执行。
 *
 *     五、maven核心概念：用好maven，了解这些概念
 *         1、POM：一个文件名称是pom.xml ，pom翻译过来叫做项目对象模型。
 *                maven把一个项目当作一个模型使用。控制maven构建项目的过程，管理jar依赖
 *         2、约定的目录结构：maven项目的目录和文件的位置都是规定的。
 *         3、坐标：是一个唯一的字符串，用来表示资源的
 *         4、依赖管理：管理你的项目可以使用jar文件
 *         5、仓库管理(了解)：你的资源存放位置
 *         6、生命周期(了解)：maven工具构架项目的过程，就是生命周期。
 *         7、插件和目标(了解)：执行maven构建的时候用的工具是插件。
 *         8、继承
 *         9、聚合
 *
 *     六、maven工具的安装和配置
 *         1、需要从maven的官网下载maven的安装包 apache-maven-3.3.9-bin.zip
 *         2、解压安装包，解压到一个目录，非中文目录。
 *             子目录 bin:执行程序，主要是mvn.cmd
 *                   conf:maven工具本身的配置文件 settings.xml
 *         3、配置环境变量
 *            在系统的环境变量中，指定一个M2_HOME的名称，指定它的值是maven工具安装目录，bin之前的目录
 *            M2_HOME=C:\Users\游家纨绔\Desktop\Ajax-Json工具包\apache-maven-3.3.9
 *            再把M2_HOME加入到path之中，在所有路径之前加入 %M2_HOME%\bin;
 *         4、验证，新的命令行中，执行mvn -v
 *            注意：需要配置JAVA_HOME,指定jdk路径
 *            出现如下内容，maven安装、配置正确
 *                C:\Users\游家纨绔>mvn -v
 *                Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-11T00:41:47+08:00)
 *                Maven home: C:\Users\游家纨绔\Desktop\Ajax-Json工具包\apache-maven-3.3.9
 *                Java version: 1.8.0_261, vendor: Oracle Corporation
 *                Java home: C:\Program Files\Java\jdk1.8.0_261\jre
 *                Default locale: zh_CN, platform encoding: GBK
 *                OS name: "windows 10", version: "10.0", arch: "amd64", family: "dos"
 *
 * 二部分(maven核心概念)
 *     一、maven约定的目录结构，约定是大家都遵循的一个规则。
 *        每一个maven项目在磁盘中都是一个文件夹(项目-Hello)
 *        Hello/
 *          ---/src
 *          ------/main           # 放你主程序Java代码和配置文件
 *          ---------java         # 你的程序包和包中的Java文件
 *          -----------resources  # 你的Java程序中要使用的配置文件
 *
 *          -----/test            # 放测试程序代码和文件的(可以没有)
 *          --------java          # 测试程序包和包中的Java文件
 *          ----------resources   # 测试Java程序中要使用的配置文件
 *
 *          ---/pom.xml           # maven的核心文件(maven项目必须有)
 *
 *     二、疑问：mvn compile 编译src/main目录下的所有Java文件的（cmd命令在项目文件src下打开）。
 *         1、为什么要下载
 *             maven工具执行的操作需要很多插件(Java类--jar文件)完成的
 *         2、下载什么东西了
 *             jar文件--叫做插件--插件是完成某些功能
 *         3、下载的东西存放到哪里了
 *             默认仓库(本机仓库)：
 *             C:\Users\游家纨绔\.m2\repository
 *
 *         Downloading:https://repo.maven.apache.org/maven2/org/apache/maven/maven-plugin-parameter-documenter-2.0.9.pom
 *         https://repo.maven.apache.org:中央仓库的地址
 *
 *         执行mvn compile,结果是在项目的根目录下生成target目录(结果目录)，
 *         maven编译的Java程序，最后的class文件都放在target目录中
 *
 *         设置本机存放资源的目录位置:
 *          1、修改maven的配置文件，maven安装目录/conf/settings.xml
 *             先备份 settings.xml
 *          2、修改local_repository 指定你的目录(不要使用中文目录)
 *
 *     三、仓库
 *          1、仓库是什么：仓库是存放东西的，存放maven使用的jar和我们项目使用的jar
 *             1)、maven使用的插件(各种jar)
 *             2)、我们项目使用的jar(第三方的的工具)
 *          2、仓库的分类
 *             1)、本地仓库，就是你的个人计算机上的文件夹，存放各种jar
 *             2)、远程仓库，在互联网上的，使用网络才能使用的仓库
 *                   ①、中央仓库，最权威的，所有的开发人员都共享使用的一个集中的仓库，
 *                       https://repo.maven.apache.org：中央仓库的地址
 *                   ②、中央仓库的镜像：就是中央仓库的备份，在各大洲，重要的城市都是镜像。
 *                   ③、私服，在公司内部，在局域网中使用的，不是对外使用的。
 *          3、仓库的使用，maven仓库的使用不需要人为参与。
 *              开发人员需要使用MySQL驱动-->maven首先查本地仓库-->私服-->镜像-->中央仓库
 *          4、pom:项目对象模型，是一个pom.xml文件
 *                 1)、坐标：唯一值，在互联网中唯一标识一个项目的
 *                        <groupId>公司域名的倒写</groupId>
 *                        <artifactId>自定义项目名称</artifactId>
 *                        <version>自定版本号</version>
 *
 *                        https://mvnrepository.com/ 搜索使用的中央仓库，使用groupId或者artifactId作为搜索
 *                 2)、packaging:打包后压缩文件的扩展名，默认是jar，web应用是war
 *                     packaging 可以不写，默认是jar
 *                 3)、依赖
 *                 dependencies 和 dependency
 *                 你的项目中要使用的各种资源说明，比如我的项目要使用MySQL驱动
 *                    <dependencies>
 *                       <dependency>
 *                          <groupId>mysql</groupId>
 *                          <artifactId>mysql-connector-java</artifactId>
 *                          <version>5.1.9</version>
 *                       </dependency>
 *                    </dependencies>
 *                  4)、properties:设置属性
 *                       <properties>
 *                          <java.version>1.8</java.version>
 *                          <maven.compiler.source>1.8</maven.compiler.source>
 *                          <maven.compiler.target>1.8</maven.compiler.target>
 *                       </properties>
 *                  5)、build：maven在进行项目的构建时，配置信息，例如指定编译Java代码使用的jdk的版本
 *          5、maven生命周期，maven的命令，maven的插件
 *             maven的生命周期：就是maven构建项目的过程,清理,编译,测试,报告,打包,安装,部署
 *             maven的命令：maven独立使用，通过命令，完成maven的生命周期的执行。
 *                         maven可以使用命令，完成项目的清理，编译，测试等等
 *             maven的插件：maven命令执行时，真正完成功能的是插件，插件就是一些jar文件，一些类。
 *
 *             Maven常见命令(不必死记硬背，使用idea可以完成以下命令)
 *             mvn clean：清理
 *             mvn compile：编译主程序(编译src/java下的文件，并会在当前目录下生成一个target，里边存放编译主程序生成的字节码文件)
 *             mvn test-compile：编译测试程序(会在当前目录下生成一个target，里边存放编译测试程序之后生成的字节码文件)
 *             mvn test：测试(会生成一个目录surefire-reports，保存测试结果)
 *             mvn package：打包主程序(会编译，编译测试，测试，并且按照pom.xml配置把主程序打包生成jar包或者war包)
 *             mvn install:安装主程序(会把本工程打包，按照本工程的坐标保存到本地仓库中)
 *             mvn deploy：部署主程序
 *
 * 三部分(Maven在idea的设置与部署)
 *     一、在idea中设置maven，让idea和maven结合使用。
 *         idea中内置了maven，一般不使用内置的，因为用内置需改maven的设置不方便。
 *         使用自己安装的maven，需要覆盖idea中的默认的设置。让idea指定maven安装位置等信息
 *
 *         配置的入口：
 *                  1、配置当前工程的设置，File--settings--Build,Excution,Deployment--Build Tools
 *                     --Maven
 *                         Maven Home directory：maven的安装目录
 *                         User Settings File：就是maven安装目录conf/setting.xml配置文件
 *                         Local Repository：本机仓库的目录位置
 *                     --Build Tools--Maven--Runner
 *                         VM Options:-DarchetypeCatalog=internal
 *                         JRE:你项目的jdk
 *
 *                        -DarchetypeCatalog=internal ,maven项目创建时，会联网下载模板文件，
 *                        比较大(8M),使用archetypeCatalog=internal，不用下载，创建maven项目速度快。
 *                  2、配置以后新建工程的设置，File--New Project Settings--Settings for New Projects--Build,Excution,Deployment--Build Tools
 *                     --Maven
 *                         Maven Home directory：maven的安装目录
 *                         USer Settings File：就是maven安装目录conf/setting.xml配置文件
 *                         Local Repository：本机仓库的目录位置
 *                     --Build Tools--Maven--Runner
 *                         VM Options:-DarchetypeCatalog=internal
 *                         JRE:你项目的jdk
 *
 *     二、使用模板创建项目
 *         1、maven-archetype-quickstart：普通的Java项目
 *         2、maven-archetype-webapp：web工程
 *            使用Maven创建web项目，不用再进行导包操作了。直接在pom.xml中进行添加依赖
 *
 * 第四部分
 *     1、依赖范围，使用scope表示的。
 *        scope的值有 compile、test、provided
 *        scope：表示依赖使用的范围，也就是在maven构建项目的那些阶段中起作用。
 *             maven构建项目  清理、编译、测试、打包、安装、部署
 *
 *      2、你在写项目中用到的所有依赖(jar),必须在本地仓库中有。
 *         没有必须通过maven下载，包括provided的都必须下载
 *
 *         你在servlet需要继承HttpServlet(provided)，你使用的HttpServlet是maven仓库中的。
 *
 *         当你写好的程序，放到Tomcat服务器中运行时，此时你的程序中不包含servlet的jar，因为tomcat提供了servlet的jar
 *
 * 第五部分：
 *    maven常用操作
 *    1、maven的属性设置
 *       <properties> 设置maven的常用属性
 *    2、maven的全局变量
 *       自定义的属性：1)、在<properties>通过自定义标签声明变量(标签名就是变量名)
 *                   2)、在pom.xml文件中的其他位置，使用 ${标签名} 使用变量的值
 *
 *       自定义全局变量一般是定义 依赖的版本号，当你的项目中要使用多个相同的版本号，
 *       先使用全局变量定义，再使用 ${变量名}
 *
 *    3、资源插件
 *      <build>
 *        <resources>
 *          <resource>
 *            <directory>src/main/java</directory>  <!--所在的目录-->
 *            <includes>
 *              <include>**/*.properties</include>
 *              <include>**/*.xml</include>
 *            </includes>
 *            <!--Maven 提供了一种过滤机制，这种机制能够在资源文件被复制到目标目录的同时，
 *                决定是否将 <directory> 指定目录下的文件中的引用（@xxx@）进行参数替换。并且这个时机是先于 compile 的一个阶段。
 *                当 filtering=true 时替换资源文件中的占位符，
 *                当 filtering=false 时不进行占位符的替换。
 *             -->
 *            <filtering>false</filtering>
 *          </resource>
 *        </resources>
 *      </build>
 *      作用：mybatis课程中会用到这个作用
 *
 *      1、默认没有使用resources的时候，maven执行编译代码时，会把src/main/resources目录中的文件拷贝到target/classes目录中。
 *         对于src/main/java目录下的非Java文件不处理，不拷贝到target/classes目录中
 *      2、我们的程序有需要把一些文件放在src/main/java目录中，当我在执行Java程序时，需要用到src/main/java目录中的文件。
 *         需要告诉maven在 mvn compile src/main/java目录下的程序时，需要把文件一同拷贝到target/classes目录中。
 *         此时就需要在<build>中加入<resources>
 *
 * 第六部分：Maven内置属性
 *    内置属性：如 ${basedir} 表示项目根目录 即包含了pom.xml文件的目录 ${version} 表示项目版本
 *
 *    pom属性：
 *           ${project.build.sourcedirectory} : 项目构建输出目录，默认为src/main/java
 *           ${project.build.testSourceDirectory} : 项目的测试源码目录，默认为src/test/java/
 *           ${project.build.directory} ：项目构建输出目录，默认为target/
 *           ${project.outputDirectory} ：项目测试代码编写输出目录。默认为target/test-calsses
 *           ${project.groupId} : 项目的groupId
 *           ${project.artifactId}
 *           ${project.version}
 *           ${project.build.finalName} :  项目打包输出文件的名称， 默认为 ${project.artifactId} - ${project.version}
 *
 *    setting属性：用户使用以setting开头的属性引用setting.xml文件中属性的值
 *                如：${setting.localRepository} 指向用户本地仓库的地址
 *
 *    Java系统属性：可使用 mvn help:system 查看所有java系统属性
 *                举例 ： ${user.home} 指向用户目录，
 *
 *    环境变量属性：可使用 mvn help:system 查看所有java系统属性
 *                举例：${env.JAVA_HOME} 指代了JAVA_HOME环境变量的值
 */
public class maven概念 {}
