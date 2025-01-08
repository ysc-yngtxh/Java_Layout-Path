# 前言
    对于后端程序员，特别是 Java 程序员来讲，排查线上问题是不可避免的。各种 CPU 飚高，内存溢出，频繁 GC 等等，这些都是令人头疼的问题。楼主同样也遇到过这些问题，那么，遇到这些问题该如何解决呢？
    
    首先，出现问题，肯定要先定位问题所在，然后分析问题原因，再然后解决问题，最后进行总结，防止下次再次出现。
    
    今天的文章，就如我们的题目一样，讲的是基本操作，也就是一些排查线上问题的基本方法。为什么这么说呢？因为线上问题千奇百怪，就算是身经百战的专家也会遇到棘手的问题，因此不可能在一篇文章里说完，还有一个最重要的原因，当然就是楼主的水平不到位。
    
    但不到位归不到位，任何经验都是值得记录的，因此，楼主有必要将这些问题记录一下。
    
    还有，本文的排查环境是 Linux.

# 1. CPU 飚高
    线上 CPU 飚高问题大家应该都遇到过，那么如何定位问题呢？
    
    思路：首先找到 CPU 飚高的那个 Java 进程，因为你的服务器会有多个 JVM 进程。然后找到那个进程中的 “问题线程”，最后根据线程堆栈信息找到问题代码。最后对代码进行排查。
    
    如何操作呢？
    
    通过 top 命令找到 CPU 消耗最高的进程，并记住进程 ID。
    再次通过 top -Hp [进程 ID] 找到 CPU 消耗最高的线程 ID，并记住线程 ID.
    通过 JDK 提供的 jstack 工具 dump 线程堆栈信息到指定文件中。具体命令：jstack -l [进程 ID] >jstack.log。
    由于刚刚的线程 ID 是十进制的，而堆栈信息中的线程 ID 是 16 进制的，因此我们需要将 10 进制的转换成 16 进制的，并用这个线程 ID 在堆栈中查找。使用 printf "%x\n" [十进制数字] ，可以将 10 进制转换成 16 进制。
    通过刚刚转换的 16 进制数字从堆栈信息里找到对应的线程堆栈。就可以从该堆栈中看出端倪。
    从楼主的经验来看，一般是某个业务死循环没有出口，这种情况可以根据业务进行修复。还有 C2 编译器执行编译时也会抢占 CPU，什么是 C2 编译器呢？当 Java 某一段代码执行次数超过 10000 次（默认）后，就会将该段代码从解释执行改为编译执行，也就是编译成机器码以提高速度。而这个 C2 编译器就是做这个的。如何解决呢？项目上线后，可以先通过压测工具进行预热，这样，等用户真正访问的时候，C2 编译器就不会干扰应用程序了。如果是 GC 线程导致的，那么极有可能是 Full GC ，那么就要进行 GC 的优化。

# 2. 内存问题排查

    说完了 CPU 的问题排查，再说说内存的排查，通常，内存的问题就是 GC 的问题，因为 Java 的内存由 GC 管理。有 2 种情况，一种是内存溢出了，一种是内存没有溢出，但 GC 不健康。
    
    内存溢出的情况可以通过加上 -XX:+HeapDumpOnOutOfMemoryError 参数，该参数作用是：在程序内存溢出时输出 dump 文件。
    
    有了 dump 文件，就可以通过 dump 分析工具进行分析了，比如常用的 MAT，Jprofile，jvisualvm 等工具都可以分析，这些工具都能够看出到底是哪里溢出，哪里创建了大量的对象等等信息。
    
    第二种情况就比较复杂了。GC 的健康问题。
    
    通常一个健康的 GC 是什么状态呢？根据楼主的经验，YGC 5 秒一次左右，每次不超过 50 毫秒，FGC 最好没有，CMS GC 一天一次左右。
    
    而 GC 的优化有 2 个维度，一是频率，二是时长。
    
    我们看 YGC，首先看频率，如果 YGC 超过 5 秒一次，甚至更长，说明系统内存过大，应该缩小容量，如果频率很高，说明 Eden 区过小，可以将 Eden 区增大，但整个新生代的容量应该在堆的 30% - 40% 之间，eden，from 和 to 的比例应该在 8：1：1 左右，这个比例可根据对象晋升的大小进行调整。
    
    如果 YGC 时间过长呢？YGC 有 2 个过程，一个是扫描，一个是复制，通常扫描速度很快，复制速度相比而言要慢一些，如果每次都有大量对象要复制，就会将 STW 时间延长，还有一个情况就是 StringTable ，这个数据结构中存储着 String.intern 方法返回的常连池的引用，YGC 每次都会扫描这个数据结构（HashTable），如果这个数据结构很大，且没有经过 FGC，那么也会拉长 STW 时长，还有一种情况就是操作系统的虚拟内存，当 GC 时正巧操作系统正在交换内存，也会拉长 STW 时长。
    
    再来看看 FGC，实际上，FGC 我们只能优化频率，无法优化时长，因为这个时长无法控制。如何优化频率呢？
    
    首先，FGC 的原因有几个，1 是 Old 区内存不够，2 是元数据区内存不够，3 是 System.gc()， 4 是 jmap 或者 jcmd，5 是 CMS Promotion failed 或者 concurrent mode failure，6 JVM 基于悲观策略认为这次 YGC 后 Old 区无法容纳晋升的对象，因此取消 YGC，提前 FGC。
    
    通常优化的点是 Old 区内存不够导致 FGC。如果 FGC 后还有大量对象，说明 Old 区过小，应该扩大 Old 区，如果 FGC 后效果很好，说明 Old 区存在了大量短命的对象，优化的点应该是让这些对象在新生代就被 YGC 掉，通常的做法是增大新生代，如果有大而短命的对象，通过参数设置对象的大小，不要让这些对象进入 Old 区，还需要检查晋升年龄是否过小。如果 YGC 后，有大量对象因为无法进入 Survivor 区从而提前晋升，这时应该增大 Survivor 区，但不宜太大。
    
    上面说的都是优化的思路，我们也需要一些工具知道 GC 的状况。
    
    JDK 提供了很多的工具，比如 jmap ，jcmd 等，oracle 官方推荐使用 jcmd 代替 jmap，因为 jcmd 确实能代替 jmap 很多功能。jmap 可以打印对象的分布信息，可以 dump 文件，注意，jmap 和 jcmd dump 文件的时候会触发 FGC ，使用的时候注意场景。
    
    还有一个比较常用的工具是 jstat，该工具可以查看 GC 的详细信息，比如 eden ，from，to，old 等区域的内存使用情况。
    
    还有一个工具是 jinfo，该工具可以查看当前 jvm 使用了哪些参数，并且也可以在不停机的情况下修改参数。
    
    包括我们上面说的一些分析 dump 文件的可视化工具，MAT，Jprofile，jvisualvm 等，这些工具可以分析 jmap dump 下来的文件，看看哪个对象使用的内存较多，通常是能够查出问题的。
    
    还有很重要的一点就是，线上环境一定要带上 GC 日志！！！
    
    总结
    基于文章的标题，我们这个是基本操作，故障排查是说不完的话题，每个故障涉及的知识也都很多，因此，我们在学习了基本的排查之后，还需要学习更多事故排查技术，比如排查 IO，网络，TCP 连接等等。楼主将在后面的文章中将这些基本操作都记录下来。

good luck！！！！

作者：stateis0

链接：https://hacpai.com/article/1519403810488

JVM（Java Virtual Machine）调优是 Java 应用程序性能优化的重要部分。通过调整 JVM 参数和优化代码，可以减少内存占用、提高垃圾回收效率、降低延迟并提升吞吐量。以下是 JVM 调优的关键点和方法：

1. 内存区域调优
   JVM 内存主要分为以下几个区域：

堆（Heap）：存储对象实例。

方法区（Metaspace）：存储类元数据、常量池等。

栈（Stack）：存储局部变量和方法调用。

本地方法栈（Native Method Stack）：用于 Native 方法调用。

程序计数器（Program Counter Register）：记录当前线程执行的字节码指令地址。

调优重点：

堆内存：调整堆大小（-Xms 和 -Xmx）。

方法区：调整 Metaspace 大小（-XX:MetaspaceSize 和 -XX:MaxMetaspaceSize）。

栈内存：调整栈大小（-Xss）。

示例：

bash
复制
java -Xms512m -Xmx2g -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -Xss256k -jar app.jar
2. 垃圾回收（GC）调优
   JVM 的垃圾回收机制对性能影响很大，选择合适的垃圾回收器和调整相关参数可以显著提升性能。

常见的垃圾回收器
Serial GC：单线程，适合小型应用。

Parallel GC：多线程，适合吞吐量优先的应用。

CMS GC：并发标记清除，适合低延迟应用。

G1 GC：分代回收，适合大内存和低延迟应用。

ZGC：低延迟，适合超大堆内存（JDK 11+）。

Shenandoah GC：低延迟，适合大内存（JDK 12+）。

常用 GC 参数
-XX:+UseSerialGC：启用 Serial GC。

-XX:+UseParallelGC：启用 Parallel GC。

-XX:+UseConcMarkSweepGC：启用 CMS GC。

-XX:+UseG1GC：启用 G1 GC。

-XX:MaxGCPauseMillis：设置最大 GC 停顿时间（如 -XX:MaxGCPauseMillis=200）。

-XX:GCTimeRatio：设置 GC 时间与应用程序时间的比例（如 -XX:GCTimeRatio=99）。

示例：

bash
复制
java -Xms2g -Xmx2g -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -jar app.jar
3. 堆内存分配调优
   新生代和老年代比例：

通过 -XX:NewRatio 设置新生代与老年代的比例（如 -XX:NewRatio=2 表示新生代占 1/3，老年代占 2/3）。

通过 -XX:SurvivorRatio 设置 Eden 区与 Survivor 区的比例（如 -XX:SurvivorRatio=8 表示 Eden 区占 80%，Survivor 区占 20%）。

对象晋升老年代的阈值：

通过 -XX:MaxTenuringThreshold 设置对象在新生代存活多少次 GC 后晋升到老年代。

示例：

bash
复制
java -Xms2g -Xmx2g -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -jar app.jar
4. 线程栈调优
   栈大小：

通过 -Xss 设置每个线程的栈大小（如 -Xss256k）。

减少线程数：

避免创建过多线程，减少栈内存占用。

示例：

bash
复制
java -Xss512k -jar app.jar
5. JIT 编译器调优
   JIT（Just-In-Time）编译器将热点代码编译为本地机器码，提升执行效率。

启用分层编译：

通过 -XX:+TieredCompilation 启用分层编译。

调整编译阈值：

通过 -XX:CompileThreshold 设置方法调用多少次后被编译。

示例：

bash
复制
java -XX:+TieredCompilation -XX:CompileThreshold=10000 -jar app.jar
6. 监控与分析工具
   JVM 内置工具：

jps：查看 Java 进程。

jstat：监控 JVM 内存和 GC 情况。

jmap：生成堆内存快照。

jstack：生成线程快照。

jconsole：图形化监控工具。

VisualVM：功能强大的性能分析工具。

第三方工具：

MAT（Memory Analyzer Tool）：分析堆内存快照。

Arthas：在线诊断工具。

Prometheus + Grafana：监控和可视化。

示例：

bash
复制
jstat -gc <pid> 1000 10  # 每 1 秒输出一次 GC 情况，共 10 次
jmap -heap <pid>         # 查看堆内存使用情况
jstack <pid>             # 生成线程快照
7. 常见问题与解决方案
   OutOfMemoryError：

增加堆内存（-Xmx）。

检查内存泄漏（使用 jmap 和 MAT 分析堆内存）。

频繁 Full GC：

调整新生代和老年代比例。

检查对象生命周期，避免过早晋升到老年代。

高 CPU 占用：

使用 jstack 分析线程状态，检查是否存在死循环或锁竞争。

8. 调优步骤总结
   监控：使用工具监控 JVM 内存、GC、线程等指标。

分析：找出性能瓶颈（如内存泄漏、频繁 GC、锁竞争等）。

调整：根据分析结果调整 JVM 参数或优化代码。

测试：在测试环境中验证调优效果。

上线：将调优后的配置应用到生产环境。

示例调优命令
bash
复制
java -Xms2g -Xmx2g -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -Xss512k -jar app.jar
通过以上方法，可以显著提升 JVM 的性能和稳定性。如果遇到复杂问题，可以结合监控工具和日志进行深入分析。
