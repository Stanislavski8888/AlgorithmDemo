/**
 * <a href="https://leetcode-cn.com/problems/remove-linked-list-elements/">203. 移除链表元素</a><br>
 * 删除链表中等于给定值 val 的所有节点。<br>
 * <pre>
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 * </pre>
 */
public class LeetCode203 {
    public static void main(String[] args) {
        int length = (int) (Math.random() * 20 + 6);
        int value = (int) (Math.random() * 10);
        System.out.println("length = " + length + ", value = " + value);
        ListNode head = ListNode.genarateSingleDigitNode(length);

        LeetCode203 instance = new LeetCode203();
//        ListNode result = instance.removeElements(head, value);
        ListNode result = instance.removeElementsOfficial(head, value);
        System.out.println("Result:");
        ListNode.printListNode(result);
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode tmp = head;
        ListNode pre = null;
        while (tmp != null) {
            if (tmp.val == val) {
                if (pre == null) {
                    ListNode next = tmp.next;
                    if (next == null) {
                        return null;
                    }
                    tmp.next = null;
                    tmp = next;
                    head = next;
                    continue;
                } else {
                    pre.next = tmp.next;
                    tmp = tmp.next;
                }
            } else {
                pre = tmp;
                tmp = tmp.next;
            }
        }

        return head;
    }

    //以下是官方解法
    public ListNode removeElementsOfficial(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.val == val) prev.next = curr.next;
            else prev = curr;
            curr = curr.next;
        }
        return sentinel.next;
    }

}
