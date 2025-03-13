package J10_集合.集合Ⅰ_Collection详解.Collection接口;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
  1、JDK5.0之后推出的新特性：泛型
  2、泛型这种语法机制，只在程序编译阶段起作用，只是给编译器参考的。（运行阶段泛型没用）
  3、使用了泛型的好处：
         第一：集合中存储的元素类型同意了
         第二：从集合中取出的元素类型是泛型指定的类型，不需要进行大量的“向下转型”！
  4、泛型的缺点：
        导致集合中存储的元素缺乏多样性！
        大多数业务中，集合元素的类型还是统一的，所以这种泛型特性被大家所认可
  5、自定义泛型的时候，<>尖括号中的是一个标识符，随便写
         Java源代码中经常出现的是：
             <E>和<T>
         E是Element元素单词首字母
         T是Type类型单词首字母
 */
public class 集合7_泛型机制<E> {
    public static void main(String[] args) {

        /*
        // 不使用泛型
        List myList = new ArrayList();
        Cat c = new Cat();
        Bird b = new Bird();

        myList.add(c);
        myList.add(b);

        Iterator it = myList.iterator();
        while(it.hasNext()){
            Object obj = it.next();
            if (obj instanceof Animal){
                Animal a = (Animal)obj;
                a.move();
            }
        }
        */

        // 使用泛型List<Animal>之后，表示List集合中只允许存储Animal类型的数据
        // 用泛型来指定集合中存储的数据类型。
        List<Animal> myList = new ArrayList<>(); // 类型自动判断(钻石表达式)。ArrayList<Animal>中的Animal可以不写

        // 指定List集合中只能存储Animal，那么存储String就编译报错了
        // 这样用了泛型之后，集合中元素的数据类型更加统一了

        myList.add(new Cat());
        myList.add(new Bird());

        // 获取迭代器，这个迭代器表示的是Animal类型
        Iterator<Animal> it = myList.iterator();
        while(it.hasNext()){
            Animal a = it.next(); // 使用泛型之后，每一次迭代返回的数据都是Animal类型
            a.move();
        }

        System.out.println("=========================================================================================");

        集合7_泛型机制<String> g = new 集合7_泛型机制<>();
        // new对象的时候指定了泛型是：String类型

        // g.doSome(100); // 类型不匹配
        g.doSome("abc");
    }
    public void doSome(E e){
        System.out.println(e);
    }   // 自定义泛型
}

class Animal{
    public void move(){
        System.out.println("动物在移动");
    }
}

class Cat extends Animal {}

class Bird extends Animal{}
