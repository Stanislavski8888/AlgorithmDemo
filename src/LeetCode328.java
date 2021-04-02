import utils.ListNode;

/**
 * <a href="https://leetcode-cn.com/problems/odd-even-linked-list/">328. 奇偶链表</a><br>
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <br>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <pre>
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * </pre>
 * <pre>
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * </pre>
 * <pre>
 * 说明:
 *
 * 1.应当保持奇数节点和偶数节点的相对顺序。
 * 2.链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * </pre>
 */
public class LeetCode328 {
    static final int LENGTH = (int)(Math.random() * 20 + 1);

    public static void main(String[] args) {
        ListNode head = ListNode.genarateListNode(LENGTH);
        LeetCode328 instance = new LeetCode328();
//        instance.oddEvenList(head);
        instance.oddEvenListOfficial(head);
        ListNode.printListNode(head);
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int index = 1;
        ListNode insert = head;
        ListNode search = head;
        ListNode pre = null;
        while (search != null) {
            if (index > 1 && index % 2 != 0) {
                ListNode next = search.next;
                search.next = insert.next;
                insert.next = search;
                pre.next = next;
                insert = search;
                search = next;
                index++;
            } else {
                index++;
                pre = search;
                search = search.next;
            }
        }
        return head;
    }

    public ListNode oddEvenListOfficial(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

}