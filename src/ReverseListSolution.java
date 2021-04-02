import utils.ListNode;

/**
 * <a href="https://leetcode-cn.com/problems/reverse-linked-list">206. 反转链表</a>
 * <p>
 * Definition for singly-linked list. <br>
 * 反转一个单链表。<br>
 * <pre>
 * 示例:
 *   输入: 1->2->3->4->5->NULL
 *   输出: 5->4->3->2->1->NULL
 * </pre>
 *
 * 进阶: <br>
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ <br>
 *
 */
public class ReverseListSolution {

    public static void main(String[] args) {
        ReverseListSolution reverseListSolution = new ReverseListSolution();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ListNode listNode = listNode1;
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }

        System.out.println();

        ListNode listNodeRev = reverseListSolution.reverseListMine(listNode1);
        while (listNodeRev != null) {
            System.out.print(listNodeRev.val + " ");
            listNodeRev = listNodeRev.next;
        }
    }

    // 我的方法
    public ListNode reverseListMine(ListNode head) {
        if (head == null) return null;
        if (head.next == null) {
            return head;
        }
        ListNode newHead = null;
        while (head.next != null) {
            ListNode temp = new ListNode(head.val);
            temp.next = newHead;
            newHead = temp;
            head = head.next;
        }
        ListNode temp = new ListNode(head.val);
        temp.next = newHead;
        newHead = temp;
        return newHead;
    }

    // 递归
    public ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // 迭代
    public ListNode reverseListIteration(ListNode head) {
        ListNode newList = new ListNode(-1);
        while(head != null){
            ListNode next = head.next;
            head.next = newList.next;
            newList.next = head;
            head = next;
        }
        return newList.next;
    }
}

/*class utils.ListNode {
    int val;
    utils.ListNode next;
    utils.ListNode(int x) {
        val = x;
    }
}*/