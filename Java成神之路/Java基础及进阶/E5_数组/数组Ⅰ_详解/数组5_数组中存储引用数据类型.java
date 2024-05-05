package E5_数组.数组Ⅰ_详解;

class Animal{
    public void move(){
        System.out.println("Animal move...");
    }
}
class Cat extends Animal{
    public void move(){
        System.out.println("Cat Catches Mouse!");
    }
    public void catchMouse(){
        System.out.println("猫抓老鼠");
    }
}
class Bird extends Animal{
    public void move(){
        System.out.println("Bird Fly!");
    }
    public void sing(){
        System.out.println("鸟儿在歌唱!");
    }
}

// 一维数组的深入，数组中存储的类型为：引用数据类型
public class 数组5_数组中存储引用数据类型 {
    public static void main(String[] args) {
        // 创建一个Animal类型的数组
        Animal a1 = new Animal();
        Animal a2 = new Animal();
        Animal[] animals = {a1, a2};

        // 对Animal数组进行遍历
        for (int i = 0; i < animals.length; i++) {
            animals[i].move();
            /*
              animals表示的是数组，animals[i]表示下标为i的元素
              不管i为0还是1，animals[i]表示的都是Animal的引用，即a1或a2
              而a1或a2都是指向Animal类的
            */
        }

        // 创建一个Animal类型的数组，数组当中存储Cat和Bird
        Animal[] anis = {new Cat(), new Bird()};
        for (int i = 0; i < anis.length; i++) {
            Animal an = anis[i];
            an.move();
            // 调用子对象特有方法的话，需要向下转型！！！
            if (anis[i] instanceof Cat){
                Cat cat = (Cat)anis[i];
                cat.catchMouse();
            } else if (anis[i] instanceof Bird){
                Bird bird = (Bird)anis[i];
                bird.sing();
            }
        }
    }
}