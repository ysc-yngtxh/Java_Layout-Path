public class idea快捷键 {
}
/*
                              _ooOoo_
                             o8888888o
                             88" . "88
                             (| -_- |)
                             O\  =  /O
                          ____/`---'\____
                        .'  \\|     |//  `.
                       /  \\|||  :  |||//  \
                      /  _||||| -:- |||||-  \
                      |   | \\\  -  /// |   |
                      | \_|  ''\---/''  |   |
                      \  .-\__  `-`  ___/-. /
                    ___`. .'  /--.--\  `. . __
                 ."" '<  `.___\_<|>_/___.'  >'"".
                | | :  `- \`.;`\ _ /`;.`/ - ` : | |
                \  \ `-.   \_ __\ /__ _/   .-` /  /
           ======`-.____`-.___\_____/___.-`____.-'======
                              `=---='
           ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                      佛祖保佑        永无BUG
*/
/*一、创建项目
 * 1、新建一个Empty project
 *     新建一个空的工程，选择创建工程窗口下面“最后的那一项”，Empty project
 *
 * 2、给空的工程起一个名字：javase
 *     存储到：C：/User/Adminidteator/Ideaprojects/javase
 *     点击finish
 *
 * 3、在空的工程下新建module（模块），Idea中的模块类似于eclipse当中的project
 *      Idea的组织方式：
 *          File-->New-->module
 *
 * 4、在New Module窗口上点击左上角的Java，然后next
 *
 * 5、给module起一个名字：chapter5
 *
 * 6、编写代码，在src目录下新建类，写代码，并运行。
 *
 *二、IDEA快捷键
 * 1、字体设置
 *   file --> settings --> 输入font --> 设置字体样式以及字号大小
 *
 * 2、快速生成main方法
 *    输入psvm
 *
 * 3、快速生成System.out.println()
 *    输入sout
 *
 * 4、注意：IDEA是自动保存，不需要CTRL+s
 *
 * 5、删除一行
 *    ctrl+y
 *
 * 6、怎么运行
 *    代码上点右键-->run
 *    或者点击左侧的绿色箭头
 *    ctrl+shift+f10
 *
 * 6、任何新增/新建/添加的快捷键是
 *    alt+insert
 *    或者鼠标右键Generate
 *
 * 7、窗口变大，变小：
 *    ctrl+alt+f12
 *
 * 8、IDEA工具中纠正错误的快捷键：alt+回车
 *
 * 9、退出项目：
 *     close project
 *
 * 10、搜索框：
 *     shift+shift
 *
 * 11、多行注释
 *     选择需要注释的内容，CTRL+shift+/
 *
 * 12、查看底层源码
 *      按住Ctrl.点击所要查看的对象。
 *      然后Ctrl+F12,调出搜索框
 *      在然后直接打出自己想要搜索的方法
 *
 * 13、后撤快捷键
 *      Ctrl+Z
 *
 * 14、多行编辑
 *      Alt+鼠标拖拽
 *
 * 15、快捷键生成序列化版本号
 *     File-->Settings-->Editor-->inspections-->搜索框中搜索serializable-->看到UID打勾，点击OK
 *     -->【注意，类名要实现Serializable接口】再在你要生成的类名上(鼠标要在类名上)alt+回车...
 *
 * 16、复制文件、类、方法的地址名
 *     鼠标右键-->Copy Reference
 *
 * 17、创建模板：File--Settings--Editor--File and Code Templates--点击+号--选择你想要创建的模板
 *
 * 18、重启idea：File--invalidate Caches/Restart--第一个是清除缓存并重启，第二个是清除缓存.....
 *
 * 19、导入Maven项目：File--Project Structure--Modules--点击+号，选择Import Module
 *     --选择你要导入的文件项目--选择Import module from external model 和 Maven--点击Finish.
 *     导入的Maven项目中main包下resources文件要转为特殊的资源文件(右键resources--Mark Directory as--Resources Root)
 *
 * 20、清理当前项目的不需要的包：Ctrl+Alt+O
 *
 * 21、在子类中重写父类的方法：ctrl+o
 *
 * 22、在Maven项目中编译出错，可能是你的模块名中含有中文。所以编译失败
 *
 * 23、安装翻译插件：选中单词或译文，按住ctrl+shift+o
 *
 * 24、将一些代码块放进一个方法中：选中你要放入的代码块+Ctrl+Alt+M
 *
 * 25、查找一个类或者一个文件中的语法错误亦或者是爆红的地方：F2
 *
 * 26、更换idea背景图片：按两下shift，输入set background image，点击进入就可以进行背景图片更换了
 *
 * 27、try/catch快捷键：Ctrl+Alt+T
 *
 * 28、代码太紧凑，可以使用：Ctrl+Alt+L
 *
 * 29、自动补全代码：Ctrl+Shift+回车
 *
 * 30、大小写相互转：Ctrl+Shift+U
 * */

/*
Java8新特性
    1、现在的方法区在Java8之前是属于堆中的一部分叫作永久区
    2、HashMap的数据结构在Java8之后的某一种情况下会由数组加链表结构变为数组加红黑树结构
    3、增加了Lambda表达式

         //原来的匿名内部类
         public void test(){
            Comparator<Integer> com = new Comparator<Integer>(){
                @Override
                public int compare(Integer o1,Integer o2){
                    return Integer.compare(o1,o2);
                }
         };

         //Lambda表达式
         public void test(){
            Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
         }

    4、强大的Stream API
    5、最大化减少空指针异常 Optional

一、Lambda表达式的基础语法：java8中引入了一个新的操作符"->" 该操作符称为箭头操作符或Lambda操作符
       箭头操作符将Lambda表达式拆分成两部分：
           左侧：Lambda表达式的参数列表，不用写数据量类型，可以省。因为JVM可以进行上下文推断出来
           右侧：Lambda表达式中所需执行的功能，即Lambda体
       Lambda表达式需要"函数式接口"的支持，函数式接口指的是接口中只有一个抽象方法的接口，可以使用注解@FunctionalInterface修饰，检查是否是函数式接口。
二、Java内置的四大核心函数式接口
       Consumer<T>:消费者接口
           void accept(T t);
       Supplier<T>:供给型接口           Math.random()*100  表示产生0-100的随机数
            T get();                  Math.random()*100+1  表示产生1-100的随机数
       Function<T,R>:函数型接口
            R apply(T t);
       Predicate<T>:断言型接口
            booean test(T t);
三、方法引用：若Lombda体中的内容有方法已经实现了，我们可以使用“方法引用”
            (可以理解为方法引用是是 Lombda表达式得另一种表现形式)
    主要有三种语法格式：
       1、对象::实例方法名
       public void test(){
            Consumer<String> con = (x) -> Sysytem.out.println(x);
            PrintStream ps = Sysytem.out;
            Consumer<String> con1 = ps::printn;
       }

       2、类::静态方法名
       pubilc void test(){
           Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
           Comparator<Integer> com1 = Integer::compare;
       }

       3、类::实例方法名
       pubilc void test(){
           BiPredicate<String,String> bp = (x,y) -> x.equals(y);
           BiPredicate<String,String> bp2 = String::equals;
       }
四、Stream API

    public void test(){
    
        //1、可以通过Collection系列集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //2、通过Arrays中的静态方法stream()获取数组流
        User[] user = new User[10];
        Stream<User> stream2 = Arrays.stream(user);

        //3、通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa","bb","cc");

        //4、创建无限流
        Stream<Integer> stream4 = Stream.iterate(0,(x) -> x+2);
        stream4.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);
    }
 */
