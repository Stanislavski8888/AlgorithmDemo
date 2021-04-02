import utils.ListNode;

/**
 * <a href="https://leetcode-cn.com/problems/reverse-nodes-in-k-group/">25. K 个一组翻转链表</a><br>
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <br>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <br>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <br>
 *  
 * <pre>
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * </pre>
 * <pre>
 * 说明：
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * </pre>
 */
public class LeetCode25 {
    static final int LENGTH = (int) (Math.random() * 20 + 1);

    public static void main(String[] args) {
        int k = (int) (Math.random() * 5 + 1);
        while (k > LENGTH || k <= 0) {
            k = (int) (Math.random() * 5 + 1);
        }

        System.out.println("LENGTH = " + LENGTH + ", k = " + k);
        ListNode head = ListNode.genarateListNode(LENGTH);

        LeetCode25 instance = new LeetCode25();
//        utils.ListNode result = instance.reverseKGroup(head, k);
        ListNode result = instance.reverseKGroupOfficial(head, k);
        System.out.println("Result:");
        ListNode.printListNode(result);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        int count = 1;
        ListNode first = head;
        ListNode second = new ListNode(-1);
        second.next = head;
        ListNode ret = null;
        while (first != null) {
            if (count == k) {
                ListNode next = first.next;
                first.next = null;
                ListNode tmp = reverseList(second.next);

                second.next = tmp;
                if (ret == null) {
                    ret = tmp;
                }
                while (tmp.next != null) {
                    tmp = tmp.next;
                }
                second = tmp;
                tmp.next = next;
                first = next;
                count = 1;
                continue;
            }
            count++;
            first = first.next;
        }
        return ret;
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
    public ListNode reverseKGroupOfficial(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }

}