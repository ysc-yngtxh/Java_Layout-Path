package J10_集合.集合Ⅰ_Collection详解.List接口;

public class 集合13_简单实现链表模型 {
    public static void main(String[] args) {
        Linked Link = new Linked();
        Link.add("abc");
        Link.add("def");
        Link.add("xyz");
        System.out.println(Link);
        System.out.println(Link.size());
    }

    static class Linked {
        // 链表中的每一个节点
        Node header;
        int size = 0;

        Linked() {}

        public int size() {
            return size;
        }

        // 向链表中添加元素
        public void add(Object data) {
            // 创建一个新的节点对象
            // 让之前单链表的末尾节点next指向新节点对象。
            // 有可能这个元素是第一个，也可能是第二个，也可能是第三个
            if (header == null) {
                // 说明还没有节点,new一个新的节点作为头节点对象
                header = new Node(data, null);
            } else {
                // 说明头节点不是空，已经存在。这时候应该找出末尾节点，让当前的末尾节点的next是新节点
                Node currentLastNode = findLast(header);
                currentLastNode.next = new Node(data, null);
            }
            size++;
        }

        // 专门查找末尾节点的方法
        private Node findLast(Node node) {
            if (node.next == null) {
                // 如果一个节点的next是null，说明这个节点就是末尾
                return node;
            }
            return findLast(node.next); // 递归方法,这里表示的是下个节点的内存地址，即下个节点的Node
        }

        // 删除链表中的某个数据的方法
        public void remove(Object obj) {}

        // 修改链表中某个数据的方法
        public void modify(Object newObj) {}

        // 查找链表中某个元素的方法
        public int find(Object obj) {
            return 1;
        }

        @Override
        public String toString() {
            StringBuffer str = new StringBuffer();
            str.append("{");
            Node data = header;
            for (int i = 0; i < size; i++) {
                str.append(data.data).append(", ");
                data = data.next;
            }
            str.append("}");
            return str.toString().replace(", }", "}");
        }
    }

    /**
     * 单链表中的节点
     *   节点是单向链表中基本的单元
     *   每一个节点Node都有两个属性
     *       一个属性：是存储的数据
     *       另一个属性：是下一个节点的内存地址
     */
    static class Node {
        // 存储的数据
        Object data;
        // 下一个节点的内存地址
        Node next;
        public Node(){}
        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
