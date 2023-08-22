package I9_集合.Collection详解2.List接口6.链表3;

/*
单链表中的节点
节点是单向链表中基本的单元
每一个节点Node都有两个属性
      一个属性：是存储的数据
      另一个属性：是下一个节点的内存地址
 */
public class Node {

    // 存储的数据
    Object data;

    // 下一个节点的内存地址
    Node next;

    public Node(){

    }

    public Node(Object data,Node next){
        this.data = data;
        this.next = next;
    }
}
