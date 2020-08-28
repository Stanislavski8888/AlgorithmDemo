/**
 * <a href="https://leetcode-cn.com/problems/reverse-linked-list-ii/">92. 反转链表 II</a>
 * <p>
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * <strong>说明:</strong>
 * <p>
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <pre>
 * <strong>示例:</strong>
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * </pre>
 */
public class LeetCode92 {
    static final int N = (int)(Math.random() * 20 + 1);
    public static void main(String[] args) {
        System.out.println("N = " + N);
        ListNode head = ListNode.genarateListNode(N);
        int m = 0;
        int n = 0;
        while (m < 1 || m > N) {
            m = (int)(Math.random() * 20);
        }
        while (n < m || n > N) {
            n = (int)(Math.random() * 19);
        }
        System.out.println("m = " + m + ", n = " + n);

        LeetCode92 instance = new LeetCode92();
        ListNode reverse = instance.reverseBetween(head, m, n);
        System.out.println("After Reverse:");
        ListNode.printListNode(reverse);

    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n || m > n) return head;

        ListNode first = head;
        ListNode second = head;
        for (int i = 1; i < n; i++) {
            first = first.next;
        }
        for (int i = 1; i <= n; i++) {
            if (i < m - 1) {
                second = second.next;
            }
        }
        ListNode reverse = reverseListIteration(second.next);
        second.next = reverse;
        while (reverse != null) {
            reverse = reverse.next;
        }
        reverse = first;

        return head;
    }

    /**
     * 链表反转（迭代法）
      */
    public ListNode reverseListIteration(ListNode head) {
        ListNode newList = new ListNode(-1);
        while (head != null) {
            ListNode next = head.next;
            head.next = newList.next;
            newList.next = head;
            head = next;
        }
        return newList.next;
    }
}
