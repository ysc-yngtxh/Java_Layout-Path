package J10_流.流Ⅵ_对象专属流;

import java.io.*;

/*
1、序列化:Serialize        java对象存储到文件中，将java对象的状态保存下来的过程
   反序列化：DeSerialize   将硬盘上的数据重新恢复到内存当中，恢复成Java对象

2、参与序列化和反序列化的对象，必须实现Serializable接口

3、注意：通过源代码发现，Serializable接口只是一个标志接口
      只是起到一个标识/标志的作用。Java虚拟机看到这个类实现了这个接口，可能会对这个类进行特殊待遇
      Serializable这个标志接口是给Java虚拟机参考的，Java虚拟机看到这个接口之后，会为该类自动生成一个序列化版本号·

4、Java语言中是采用什么机制来区分类的？
          第一：首先通过类名进行比对，如果类名不一样，肯定不是同一个类
          第二：如果类名一样，再进行序列化版本号进行区分

5、自动生成序列化版本号的缺陷：
     一旦代码确定之后，不能进行后续的修改，因为只要修改，必然会重新编译，
     此时会生成全新的序列化版本号，这个时候Java虚拟机会认为这是一个全新的类（这样就不好了）

     最终结论：
           凡是一个类实现了Serializable接口，建议给该类提供一个固定不变的序列化版本
           这样，以后这个类即使代码修改了，但是版本号不变，Java虚拟机会认为是同一个类

6、如何应用呢？
      比如我在逛淘宝，在购物车栏正准备付款，但是由于其他事情我离开了。
      这时候淘宝就会将我的数据全部序列化存储到文件中，在下次我逛淘宝时候恢复内存，恢复成Java对象
      但是现在一般不怎么应用了，因为有数据库
 */
public class 流_15_序列化与反序列化的实现 {
    public static void main(String[] args) throws Exception{

        // 创建Java对象
        Student s = new Student(111, "游诗成", "男");
        // 序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Student"));
        // 序列化对象
        oos.writeObject(s);

        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Student"));
        Object obj = ois.readObject();
        // 反序列化回来时一个学生对象，所以会调用学生对象的toString方法1
        System.out.println(obj);
        oos.close();
        oos.flush();
    }
}

class Student implements Serializable {

    // IDEA工具快捷键生成的固定序化版本号
    private static final long serialVersionUID = -3199561984941795299L;

    private int no;
    private String name;

    // **transient关键字是不参加序列化操作
    private transient String sex;

    public Student() {
    }

    public Student(int no, String name, String sex) {
        this.no = no;
        this.name = name;
        this.sex = sex;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
