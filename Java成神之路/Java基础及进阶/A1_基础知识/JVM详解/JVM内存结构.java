package A1_基础知识.JVM详解;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-06-14 08:00:00
 */
public class JVM内存结构 {
	/**
	 * 一、JVM 的组织架构 大致可以划分为三个部分
	 *     分别是类加载器（Class Loader）、运行时数据区（Runtime Data Areas）和执行引擎（Execution Engine）
	 *     1. 类加载器（Class Loader）
	 *        类加载器是 JVM 的一部分，负责将类文件（字节码文件【.class文件】）加载到内存中。它主要经历以下几个阶段：
	 *            - 加载（Loading）：将类文件加载到内存中，并生成 Class 对象。
	 *            - 连接（Linking）：将类的符号引用转换为直接引用，主要包括验证、准备和解析三个步骤。
	 *            - 初始化（Initialization）：执行类的静态初始化代码，包括静态变量的赋值和静态代码块的执行。
	 *        类加载器分为以下几种：
	 *            - 启动类加载器（Bootstrap Class Loader）：负责加载 Java 核心类库，如 rt.jar。
	 *            - 扩展类加载器（Extension Class Loader）：负责加载 Java 扩展类库，如 jre/lib/ext 目录下的类。
	 *            - 应用类加载器（Application Class Loader）：负责加载应用程序的类路径下的类。
	 *            - 自定义类加载器（Custom Class Loader）：用户可以自定义类加载器，以实现特定的类加载逻辑。
	 *        类加载器的工作原理如下：
	 *            ①、当 JVM 启动时，首先加载启动类加载器。
	 *            ②、启动类加载器加载 Java 核心类库，并将其存储在方法区的类信息中。
	 *            ③、当应用程序需要加载其他类时，应用类加载器会根据类路径查找类文件，并将其加载到内存中。
	 *            ④、如果应用类加载器无法找到类文件，则会委托给扩展类加载器和启动类加载器进行加载。
	 *            ⑤、类加载器还会处理类的继承关系和命名空间，以确保类的唯一性和可见性。
	 *     2. 运行时数据区（Runtime Data Areas）
	 *        运行时数据区是 JVM 在运行时使用的内存区域，主要包括方法区、堆、虚拟机栈、本地方法栈和程序计数器等。
	 *        ①、方法区（Method Area）
	 *           方法区是 JVM 中的一个内存区域，用于存储类信息、常量、静态变量等数据。它是所有线程共享的区域。
	 *           方法区的主要作用是存储类的结构信息，包括类的字段、方法、运行时常量池等。
	 *           方法区的内存分配和回收由 JVM 自动管理，通常使用永久代（PermGen，Jdk1.7）或元空间（Metaspace，Jdk1.8）来实现。
	 *        ②、堆（Heap）
	 *           堆是 JVM 中最大的内存区域，用于存储对象实例。它是所有线程共享的区域。
	 *           堆的主要作用是存储对象的实例数据，包括对象的字段、数组等。
	 *           堆的内存分配和回收由 JVM 自动管理，通常使用垃圾回收器（Garbage Collector）来实现。
	 *        ③、虚拟机栈（Java Virtual Machine Stack）
	 *           虚拟机栈是每个线程都有自己的内存区域，用于存储局部变量、操作数栈、动态链接等信息。
	 *           虚拟机栈的主要作用是存储方法调用的上下文信息，包括方法的参数、局部变量、返回值等。
	 *           虚拟机栈的内存分配和回收由 JVM 自动管理，通常使用栈帧（Stack Frame）来实现。
	 *        ④、本地方法栈（Native Method Stack）
	 *           本地方法栈与虚拟机栈类似，但用于处理本地方法调用（native方法，调用C++实现的本地方法）。
	 *           本地方法栈的主要作用是存储本地方法调用的上下文信息，包括方法的参数、局部变量、返回值等。
	 *           本地方法栈的内存分配和回收由 JVM 自动管理，通常使用栈帧来实现。
	 *        ⑤、程序计数器（Program Counter Register）
	 *           程序计数器是每个线程都有自己的内存区域，用于记录当前线程执行的字节码指令地址。
	 *           程序计数器的主要作用是记录当前线程执行的字节码指令的地址，以便在方法调用和返回时能够正确地跳转到相应的位置。
	 *     3. 执行引擎（Execution Engine）
	 *        执行引擎是 JVM 的核心部分，负责执行字节码指令。它将字节码转换为机器码，并在 CPU 上执行。
	 *        执行引擎主要包括解释器和即时编译器（JIT Compiler）。
	 *        解释器逐条解释执行字节码指令，而即时编译器则将热点代码编译为机器码，以提高执行效率。
	 *        执行引擎还包括垃圾回收器（Garbage Collector），用于管理内存的分配和回收。
	 *
	 * 二、永久代（PermGen）与元空间（MetaSpace）的区别
	 *     1、在 Jdk1.7 之前，JVM 使用永久代（PermGen）来存储类的结构信息、常量池、静态变量等数据。
	 *        永久代是 JVM 的一部分，使用 JVM 的内存（Heap）来存储这些数据。
	 *        永久代的大小是固定的，通常在 JVM 启动时通过 -XX:PermSize 和 -XX:MaxPermSize 参数进行配置。
	 *        如果永久代的大小不足以存储所有的类信息，可能会导致 OutOfMemoryError 异常。
	 *     2、在 Jdk1.8 之后，JVM 引入了元空间（MetaSpace）来替代永久代。
	 *        元空间使用本地内存（Native Memory）来存储类的结构信息、常量池、静态变量等数据。
	 *        元空间不属于 JVM 内存，不受 JVM 垃圾回收的控制。
	 *        元空间的大小取决于系统内存，而不是堆大小。
	 *        元空间的大小可以通过 -XX:MetaspaceSize 和 -XX:MaxMetaspaceSize 参数进行配置。
	 *     3、为什么要使用元空间？
	 *        原因：避免OOM异常
	 *             使用永久代时
	 *                使用永久代时，通常使用 -XX:PermSize 和 -XX:MaxPermSize 设置永久代的初始大小和设置永久代的最大值，
	 *                但是不是总能知道应该设置为多大合适（太小，容易导致永久代溢出；太大，容易导致老年代溢出），
	 *                如果使用默认值很容易遇到OOM(OutOfMemory)错误。
	 *             使用元空间时
	 *                JDK8 的方法区是元空间，元空间可以加载多少类的元数据由系统的实际可用空间来控制。
	 *                元空间使用本地内存(Native memory)。元空间使用本地内存也就意味着只要本地内存足够，就不会出现OOM的错误。
	 *                默认情况下元空间大小是无限的。
	 *     总结：
	 *     永久代和元空间的主要区别在于存储方式和内存管理：
	 *        永久代使用 JVM 的内存（Heap）来存储类信息，而元空间使用本地内存（Native Memory）。
	 *        永久代的大小是固定的，而元空间的大小取决于系统内存。
	 *        永久代受 JVM 垃圾回收的控制，而元空间不受 JVM 垃圾回收的控制。
	 *        永久代和元空间的主要作用是存储类的结构信息、常量池、静态变量等数据。
	 *        永久代和元空间的内存分配和回收由 JVM 自动管理，但它们的实现方式不同。
	 *        永久代和元空间的引入是为了提高 JVM 的性能和内存管理能力。
	 *
	 * 三、方法区中的运行时常量池
	 *    运行时常量池（Runtime Constant Pool）是 Java 虚拟机（JVM）方法区的一部分，用于存储编译期生成的常量和运行时生成的常量。
	 *    每一个 class 文件都存在一个常量池表，而每个类或接口被加载后，都会进入对应的运行时常量池。
	 *    运行时常量池的主要作用是提供一个共享的常量池，以便在运行时可以快速访问和使用这些常量。
	 *
	 *    运行时常量池是方法区的一部分，它主要用于存储以下几种类型的常量：
	 *       1、数字常量：在编译期生成的数字常量（如整数、浮点数等）会被存储在运行时常量池中。
	 *       2、类和接口的符号引用：在编译期生成的类和接口的符号引用（如类名、方法名、字段名等）会被存储在运行时常量池中。
	 *       3、方法和字段的符号引用：在编译期生成的方法和字段的符号引用（如方法名、参数类型、返回类型等）会被存储在运行时常量池中。
	 *       4、动态生成的常量：在运行时动态生成的常量（如通过反射生成的类、方法、字段等）也会被存储在运行时常量池中。
	 *       5、Jdk1.6之前【字符串常量】是存储在方法区实现的永久代下的运行时常量池中，Jdk1.7之后 字符串常量池 被移至堆。
	 *
	 * 四、堆中的新生代与老年代
	 *    堆是 Java 虚拟机（JVM）中最大的内存区域，用于存储对象实例。
	 *    堆中的内存可以分为新生代（Young Generation）和老年代（Old Generation）两部分。
	 *    新生代是用于存储新创建的对象实例的内存区域，而老年代是用于存储长时间存活的对象实例的内存区域。
	 *    新生代和老年代的主要区别在于对象的生命周期和垃圾回收策略：
	 *    新生代中的对象通常是短生命周期的对象，垃圾回收器会频繁地对新生代进行垃圾回收，以释放内存空间。
	 *    老年代中的对象通常是长生命周期的对象，垃圾回收器会较少地对老年代进行垃圾回收，以减少内存的分配和回收开销。
	 *    新生代和老年代的内存分配和回收由 JVM 自动管理，通常使用垃圾回收器（Garbage Collector）来实现。
	 *    新生代和老年代的内存分配和回收策略可以通过 JVM 参数进行配置，如 -Xmx、-Xms、-Xmn、-XX:NewSize、-XX:MaxNewSize 等。
	 *
	 */
}
