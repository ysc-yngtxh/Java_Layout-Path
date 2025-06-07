package S19_编译机试题;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-28 18:00
 * @apiNote TODO 给定一个链表，再给定一个整数 pivot，请将链表调整为左部分都是值小于 pivot 的节点，
 *               中间部分都是值等于 pivot 的节点，右边部分都是大于 pivot 的节点。主体结构满足要求即可，对部分内部的节点顺序不做要求。
 */
public class J_链表结构 {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
            this.next = null;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int pivot = scanner.nextInt();
        scanner.nextLine();
        String nextLine = scanner.nextLine();
        String[] strList = nextLine.trim().split(", ");

		Node head = new Node(Integer.parseInt(strList[0]));
		Node cur = head;
		for (int i = 1; i < strList.length; i++) {
			cur.next = new Node(Integer.parseInt(strList[i]));
			cur = cur.next;
		}
		head = partition(head, pivot);
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
	}

	private static Node partition(Node node, int target) {
		Node less = new Node(-1);
		Node equal = new Node(-1);
		Node great = new Node(-1);
		Node head1 = less;
		Node head2 = equal;
		Node head3 = great;
		while (node != null) {
			if (node.value < target) {
				less.next = new Node(node.value);
				less = less.next;
			} else if (node.value > target) {
				great.next = new Node(node.value);
				great = great.next;
			} else {
				equal.next = new Node(node.value);
				equal = equal.next;
			}
			node = node.next;
		}
		if (head3.next != null) {
			equal.next = head3.next;
			head3.next = null;       // 把大于链表头部的哑节点断开
		}
		if (head2.next != null) {
			less.next = head2.next;
			head2.next = null;       // 把等于链表头部的哑节点断开
		}
		return head1.next;
	}

}
