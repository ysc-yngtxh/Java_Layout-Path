package K11_流.流Ⅵ_对象专属流;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
  1、序列化：  Serialize        java对象存储到文件中，将java对象的状态保存下来的过程
           例如：①、将Student对象作为新增数据写入Mysql中；②、通过SpringMvc将Student对象转为Json返回给客户端
     反序列化：DeSerialize      将硬盘上的数据重新恢复到内存当中，恢复成Java对象
           例如：①、将Mysql中查询到的数据重新恢复到内存中，恢复成Student对象；②、还有前端传过来的Json数据，转为Student对象

  2、参与序列化和反序列化的对象，必须实现 Serializable 接口
　
  3、注意：通过源代码发现，Serializable接口只是一个标志接口
          只是起到一个标识/标志的作用。Java虚拟机看到这个类实现了这个接口，可能会对这个类进行特殊待遇
          Serializable这个标志接口是给Java虚拟机参考的，Java虚拟机看到这个接口之后，会为该类自动生成一个序列化版本号·

  4、Java语言中是采用什么机制来区分类的？
            第一：首先通过类名进行比对，如果类名不一样，肯定不是同一个类
            第二：如果类名一样，再进行序列化版本号进行区分

  5、为什么需要自己手动定义序列化的 serialVersionUID 变量，而不使用JVM的自动生成序列化版本号？
       ①、比如有一个 Student 类，我就正常实现 Serializable接口，但不手动定义序列化版本号。
           在 Student类编译时JVM会自动生成序列化版本号--A，随后我将 Student类 进行序列化处理。
       ③、序列完之后，我在 Student类 中新增一个属性字段(address)。由于 Student类 进行了修改操作，JVM会重新编译。
           因为进行了重新编译，Java虚拟机会认为这是一个全新的类，此时会为 Student类 生成全新的序列化版本号--B。
       ④、然后我再去操作 Student类 的反序列化。因为我们类名可能是一样的，但是我们的序列化版本号不一样，
           尝试用新的类去反序列化旧的对象数据就会失败。就会出现 java.io.InvalidClassException异常 报错。

       因此，为了避免可能出现的异常问题，需要我们在实现序列化Serializable接口的时候，自己手动定义该类的序列化版本号。

  6、应用场景：
        1、需要把内存中的对象状态数据保存到一个文件或者数据库中的时候，这个场景是比较常见的。
           例如我们利用 mybatis框架 编写持久层 insert对象数据到数据库中时;
        2、网络通信时需要用套接字在网络中传送对象时，如我们使用RPC协议进行网络通信时;
 */
public class 流15_序列化与反序列化的实现 {
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
        oos.flush();
        oos.close();
    }
}

class Student implements Serializable {

    // IDEA工具快捷键生成的固定序化版本号
    private static final long serialVersionUID = -3199561984941795299L;

    private int no;
    private String name;

    // transient关键字是不参加序列化操作
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
