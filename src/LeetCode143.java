import utils.ListNode;

import java.util.ArrayList;

/**
 * <a href="https://leetcode-cn.com/problems/reorder-list/">143. 重排链表</a><br>
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，<br>
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <br>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <br>
 * <pre>
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * </pre>
 * <pre>
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * </pre>
 */
public class LeetCode143 {
    static final int LENGTH = (int) (Math.random() * 20 + 1);

    public static void main(String[] args) {
        System.out.println("LENGTH = " + LENGTH);
        ListNode head = ListNode.genarateListNode(LENGTH);

        LeetCode143 instance = new LeetCode143();
        instance.reorderList(head);

        ListNode.printListNode(head);
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return ;
        }

        ListNode tmp = head;
        int length = 0;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        System.out.println("length = " + length);

        int breakPoint = length / 2;
        tmp = head;
        length = 0;
        ListNode reverseNode = null;
        while (tmp != null) {
            length++;
            if (length == breakPoint) {
                ListNode point = tmp.next;
                tmp.next = null;
                reverseNode = reverseList(point);
                break;
            }
            tmp = tmp.next;
        }

        while (head != null && reverseNode != null) {
            ListNode oldHeadNext = head.next;
            head.next = reverseNode;
            ListNode oldReverseNext = reverseNode.next;
            if (oldHeadNext != null) {
                reverseNode.next = oldHeadNext;
            }
            reverseNode = oldReverseNext;
            head = oldHeadNext;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode newList = new ListNode(-1);
        while(head != null){
            ListNode next = head.next;
            head.next = newList.next;
            newList.next = head;
            head = next;
        }
        return newList.next;
    }

    //以下是官方解法
    public void reorderListOfficial(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseListOfficial(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseListOfficial(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }

}