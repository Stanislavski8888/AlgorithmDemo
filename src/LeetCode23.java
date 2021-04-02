import utils.ListNode;

/**
 * <a href="https://leetcode-cn.com/problems/merge-k-sorted-lists/">23. 合并K个升序链表</a><br>
 * 给你一个链表数组，每个链表都已经按升序排列。<br>
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。<br>
 *
 * <pre>
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * </pre>
 * <pre>
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 * </pre>
 */
public class LeetCode23 {
    static final int LISTNODE_LENGTH = (int) (Math.random() * 10 + 1);

    public static void main(String[] args) {
        System.out.println("LISTNODE_LENGTH = " + LISTNODE_LENGTH);
        ListNode[] listNodes = new ListNode[LISTNODE_LENGTH];
        for (int i = 0; i < LISTNODE_LENGTH; i++) {
            int length = (int) (Math.random() * 10 + 1);
            System.out.println("List: " + i + " --> length: " + length);
            ListNode listNode = ListNode.genarateListNode(length);
            ListNode sortNode = ListNode.sortList(listNode);
            listNodes[i] = sortNode;
            ListNode.printListNode(sortNode);
        }

        LeetCode23 instance = new LeetCode23();
        ListNode result = instance.mergeKLists(listNodes);
        System.out.println("Result:");
        ListNode.printListNode(result);

    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        int length = lists.length;
        ListNode head = lists[0];
        for (int i = 0; i < length - 1; i++) {
            head = mergeTwoLists(head, lists[i + 1]);
        }
        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode tmp = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tmp.next = l1;
                l1 = l1.next;
            } else {
                tmp.next = l2;
                l2 = l2.next;
            }
            tmp = tmp.next;
        }
        tmp.next = l1 == null ? l2 : l1;
        return head.next;
    }
}