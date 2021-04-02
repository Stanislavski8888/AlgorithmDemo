import utils.ListNode;

/**
 * <a href="https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/">19. 删除链表的倒数第N个节点</a>
 * <p>
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。 <p>
 * <strong>示例：</strong>
 * <pre>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * </pre>
 * <strong>说明：</strong>
 * <pre>
 * 给定的 n 保证是有效的。
 * </pre>
 * <strong>进阶：</strong>
 * <pre>
 * 你能尝试使用一趟扫描实现吗？
 * </pre>
 */

public class LeetCode19 {
    static int N = (int) (Math.random() * 20 + 1);

    public static void main(String[] args) {
        System.out.println("N = " + N);
        ListNode head = ListNode.genarateListNode(N);

        LeetCode19 instance = new LeetCode19();
        int n = (int) (Math.random() * 20);
        while (n > N || n <= 0) {
            n = (int) (Math.random() * 20);
        }
        System.out.println("n = " + n);
//        utils.ListNode result = instance.removeNthFromEnd(head, n);
        ListNode result = instance.removeNthFromEndOfficial(head, n);
        while (result != null) {
            System.out.print(result.val + " -> ");
            result = result.next;
        }
        System.out.print("null");
    }

    /**
     * 我的方法。（有问题）
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0;
        ListNode first = head;
        ListNode second = head;
        while (first != null && count < n) {
            first = first.next;
            ++count;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }
        System.out.println("second.next.next = " + second.next.next.val);
        second.next = second.next.next;
        System.out.println("second.next = " + second.next.val);
        return head;
    }

    /**
     * 官方解法.
     */
    public ListNode removeNthFromEndOfficial(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}