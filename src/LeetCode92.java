import utils.ListNode;

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
    static final int N = (int) (Math.random() * 20 + 1);

    public static void main(String[] args) {
        System.out.println("N = " + N);
        ListNode head = ListNode.genarateListNode(N);
        int m = 0;
        int n = 0;
        while (m < 1 || m > N) {
            m = (int) (Math.random() * 20);
        }
        while (n < m || n > N) {
            n = (int) (Math.random() * 19);
        }
        System.out.println("m = " + m + ", n = " + n);

        LeetCode92 instance = new LeetCode92();
//        utils.ListNode reverse = instance.reverseBetween(head, m, n);
        ListNode reverse = instance.reverseBetweenOfficial(head, m, n);
//        utils.ListNode reverse = instance.reverseBetween(head, m, n);
        System.out.println("After Reverse:");
        ListNode.printListNode(reverse);

    }

    /**
     * 我的方法
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n || m > n) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        ListNode temp = null;
        for (int i = 1; i <= n; i++) {
            first = first.next;
        }
        ListNode tail = first.next;
        for (int i = 1; i <= n + 1; i++) {
            if (i == m) {
                temp = second.next;
                second.next = first;
                second = temp;
            } else if (i > m) {
                temp = second.next;
                second.next = tail;
                tail = second;
                second = temp;
            } else {
                second = second.next;
            }
        }

        return dummy.next;
    }

    /**
     * 官方方法一: 迭代链接反转
     */
    public ListNode reverseBetweenOfficial(ListNode head, int m, int n) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }


    /**
     * 官方方法二: 递归
     */
    public ListNode reverseBetweenAuthority(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }

    // Object level variables since we need the changes
    // to persist across recursive calls and Java is pass by value.
    private boolean stop;
    private ListNode left;

    public void recurseAndReverse(ListNode right, int m, int n) {

        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }

}