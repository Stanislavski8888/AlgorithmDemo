import utils.ListNode;

/**
 * <a href="https://leetcode-cn.com/problems/rotate-list/">61. 旋转链表</a><br>
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <br>
 * <pre>
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * </pre>
 *
 * <pre>
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * </pre>
 */
public class LeetCode61 {
    static final int K = (int) (Math.random() * 100);
    static final int LENGTH = (int) (Math.random() * 20 + 1);

    public static void main(String[] args) {
        System.out.println("K = " + K + ", LENGTH = " + LENGTH);
        ListNode listNode = ListNode.genarateListNode(LENGTH);

        LeetCode61 instance = new LeetCode61();
        ListNode result = instance.rotateRight(listNode, K);
//        utils.ListNode result = instance.rotateRightOfficial(listNode, K);
        System.out.println("Result:");
        ListNode.printListNode(result);
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode newHead = head;
        ListNode tmp = head;
        int length = 1;
        while (newHead.next != null) {
            newHead = newHead.next;
            length++;
        }

        newHead.next = head;
        int count = length - k % length;
        if (count == 0) {
            return head;
        }
        while (tmp != null && tmp.next != null) {
            if (count == 1) {
                newHead = tmp.next;
                tmp.next = null;
            } else {
                count--;
            }
            tmp = tmp.next;
        }
        return newHead;
    }

    public ListNode rotateRightOfficial(ListNode head, int k) {
        // base cases
        if (head == null) return null;
        if (head.next == null) return head;

        // close the linked list into the ring
        ListNode old_tail = head;
        int n;
        for(n = 1; old_tail.next != null; n++)
            old_tail = old_tail.next;
        old_tail.next = head;

        // find new tail : (n - k % n - 1)th node
        // and new head : (n - k % n)th node
        ListNode new_tail = head;
        for (int i = 0; i < n - k % n - 1; i++)
            new_tail = new_tail.next;
        ListNode new_head = new_tail.next;

        // break the ring
        new_tail.next = null;

        return new_head;
    }

}