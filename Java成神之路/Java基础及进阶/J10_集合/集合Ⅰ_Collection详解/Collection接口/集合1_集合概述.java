package J10_集合.集合Ⅰ_Collection详解.Collection接口;

/* 集合概述
 *    1、什么是集合？有什么用？
 *       数组其实就是一个集合。集合实际上既是一个容器。可以来容纳其他类型的数据
 *
 *       集合为什么说在开发中使用较多？
 *            集合是一个容器，是一个载体，可以一次容纳多个对象
 *            在实际开发中，假设连接数据库，数据库当中有10条记录，
 *            那么假设把这10条记录查询出来，在Java程序中会将10条数据封装成10个Java对象，
 *            然后将10个对象放到某一个集合当中，将集合传到前端，然后遍历集合，将一个数据一个数据展现出来
 *
 *    2、集合不能直接存储基本数据类型，另外集合也不能直接存储Java对象，
 *       集合当中存储的都是Java对象的内存地址。（或者说集合中存储的是引用）
 *           list.add(100);   // 自动装箱Integer
 *           注意：
 *               集合在Java中本身是一个容器，是一个对象
 *               集合中任何时候存储的都是“引用”
 *
 *    3、在Java中每一个不同的集合，底层会对应不同的数据结构。往不同的集合中存储元素，等于将数据放到了不同的数据结构中。
 *       什么是数据结构？数据存储的结构就是数据结构。不同的数据结构，数据存储方式不同。
 *       例如： 数组、二叉树、链表、哈希表。。。 这些都是常见的数据结构
 *
 *       你往集合c1中放数据，可能是放到数组上了
 *       你往集合c2中放数据，可能是放到二叉树上了
 *       。。。
 *       你使用不同的集合等同于使用了不同的数据结构。
 *
 *       你在Java集合这一章节，你需要掌握的不是精通数据结构。Java中已经将数据结构实现了，已经写好了这些常用的集合类，
 *       你只需要掌握怎么用？在什么情况下选择哪一种合适的几何去使用即可
 *
 *       new ArrayList();   创建一个集合，底层是数组
 *       new LinkedList();  创建一个集合对象，底层是链表
 *       new TreeSet();     创建一个集合对象，底层是二叉树
 *
 *    4、集合在Java JDK中哪个包下？
 *       java.util.*;
 *           所有的集合类和集合接口都在java.util包下
 *
 *    5、为了让大家掌握集合这块的内容，最好能将集合的继承结构图背会！！！
 *       集合整个这个体系是怎样的一个结构，你需要有印象。
 *
 *    6、在Java中集合分为两大类：
 *       ①、一类是单个方式存储元素：
 *             单个方式存储元素，这一类集合中超级父类：java.util.Collection;
 *
 *       ②、一类是以键值对的方式存储元素
 *             以键值对的方式存储元素，这一类集合中超级父类接口：java.util.Map;
 *
 */
public class 集合1_集合概述 {
	/*
	 *  Map集合
	 *     1、Map集合和Collection集合没有关系
	 *     2、Map集合以key和value这种键值对的方存储元素
	 *     3、key和value都是存储java对象的内存地址
	 *     4、所有Map集合的key特点：无序不可重复的。Map集合的key和Set集合存储元素特点相同
	 */
}
