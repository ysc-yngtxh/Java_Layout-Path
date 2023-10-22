package E5_数组.数组Ⅱ_作业;

import java.util.Scanner;

/*
 第二题：
    为某个酒店编写程序：酒店管理系统，模拟订房、退房、打印所有房间状态 等功能。
         1、该系统的用户是：酒店前台。
         2、酒店中所有的房间使用一个二维数组来模拟
         3、酒店中的每一个房间应该是一个Java对象：Room
         4、每一个房间Room应该有：房间编号，房间类型，房间是否空闲
         5、系统应该对外提供的功能：
               可以预定房间：用户输入房间编号，订房
               可以退房：用户输入房间编号，退房。
               可以查看所有房间的状态：用户输入某个指令应该可以查看所有房间状态
 */
public class 数组9_数组作业第二题 {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        System.out.println("欢迎使用酒店系统,请认真阅读以下使用说明");
        System.out.println("功能编号对应的功能：[1]表示查看房间列表、[2]表示订房、[3]表示退房、[0]表示退出系统");
        Scanner s = new Scanner(System.in);  // 我们可以通过 Scanner 类来获取用户的输入。

        // while死循环，可以一直用
        while(true) {
            System.out.print("请输入功能编号：");
            int i = s.nextInt();         // 这个表示吸取输入台输入的int类型
            // 还有String st = s.nextLine();表示的是吸取输入台输入的字符串类型
            if(i == 1) {
                // 查看房间列表
                hotel.print();
            } else if(i == 2) {
                System.out.print("请输入订房编号：");
                int roomNo = s.nextInt();
                hotel.order(roomNo);
            } else if(i == 3) {
                System.out.print("请输入退房编号：");
                int roomNo = s.nextInt();
                hotel.exit(roomNo);
            } else if(i == 0) {
                System.out.print("再见，欢迎下次再来！");
                return;
            } else {
                // 出错了
                System.out.println("输入功能编号有误，请重新输入！");
            }
        }
    }
}
class Hotel{
    private Room[][] rooms;

    public Hotel() {
        // 设置酒店房间：酒店三层，每层5间房子
        this.rooms = new Room[3][5];
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if(i == 0) {
                    rooms[i][j] = new Room( (i+1) * 100 + j + 1, "单人间", true );
                } else if(i == 1) {
                    rooms[i][j] = new Room( (i+1) * 100 + j + 1, "双人间", true );
                } else if(i == 2){
                    rooms[i][j] = new Room( (i+1) * 100 + j + 1, "总统套间", true );
                }
            }
        }
    }

    // 在酒店对象上提供一个打印房间列表的方法
    public void print(){
        // 打印所有房间状态，就是遍历二维数组
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                Room room = rooms[i][j];
                // 在System.out.println()中插入引用，系统会自动调用toString()方法，但我们在Room中重写了toString()方法
                System.out.print(room);
            }
            // 换行
            System.out.println();
        }
    }

    public void order(int roomNo){
        // 订房最主要的是将房间对象的status修改为false
        // 假设房间编号205,下标是rooms[1][4]。都是实例类，没必要new对象
        Room room = rooms[roomNo/100-1][roomNo%100-1];
        // 修改为占用
        room.setStatus(false);
        System.out.println(roomNo + "已订房！");
    }

    public void exit(int roomNo){
        Room room = rooms[roomNo/100-1][roomNo%100-1];
        // 修改为空闲
        room.setStatus(true);
        System.out.println(roomNo + "已退房！");
    }
}

class Room{
    private int no;          // 房间编号
    private String type;     // 房间类型
    private boolean status;  // 房间状态

    public Room() {
    }

    public Room(int no, String type, boolean status) {
        this.no = no;
        this.type = type;
        this.status = status;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // IDEA工具对于Boolean类型的变量，生成的get方法的方法名是：isXxx()
    // 如果不喜欢这种方式，可以修改为getXxx()
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // equals重写
    // equals是用来比较两个对象是否相同的
    // 至于怎么比较，这个还是程序员自己定。
    // 你认为两个房间的编号相同，就表示同一个房间，那么你写代码比较房间编号就行
    @Override
    public boolean equals(Object obj) {
        if( obj == null || !(obj instanceof Room) ) {
            return false;
        } if(this == obj) {
            return true;
        }
        Room room = (Room)obj;
        // 当前房间编号等于传过来的房间编号
        return this.getNo()==room.getNo();
    }

    // toString重写
    // toString方法的目的就是将Java对象转换成字符串的形式
    // 怎么转，转换成什么格式，程序员自己定。目的就是：简单、清晰明了。
    @Override
    public String toString() {
        /* return "[101, 单人间, 占用]"; */
        return "["+ no +" , "+ type +" , "+ (status ? "空闲" : "占用") +"]";
    }
}
