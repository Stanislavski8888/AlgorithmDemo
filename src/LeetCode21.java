/**
 * <a href="https://leetcode-cn.com/problems/merge-two-sorted-lists/">21. 合并两个有序链表</a><br>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <br>
 * <pre>
 * <strong>示例：</strong>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * </pre>
 */
public class LeetCode21 {
    static final int LENGTH1 = (int) (Math.random() * 10 + 1);
    static final int LENGTH2 = (int) (Math.random() * 10 + 1);

    public static void main(String[] args) {
        System.out.println("LENGTH1 = " + LENGTH1 + ", LENGTH2 = " + LENGTH2);
        ListNode listNode1 = ListNode.genarateListNode(LENGTH1);
        ListNode listNode2 = ListNode.genarateListNode(LENGTH2);

        LeetCode21 instance = new LeetCode21();
        ListNode sortList1 = instance.sortList(listNode1);
        ListNode sortList2 = instance.sortList(listNode2);

        System.out.println("Sorted:");
        ListNode.printListNode(sortList1);
        ListNode.printListNode(sortList2);

        System.out.println("Merged:");
        ListNode mergeList = instance.mergeTwoLists(sortList1, sortList2);
        ListNode.printListNode(mergeList);
    }

    public ListNode mergeTwoListsOfficial(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
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

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ret = null;
        ListNode pre = null;
        while (head != null) {
            ListNode smallestPre = getSmallPre(head);
            if (ret == null) {
                ret = smallestPre == null ? head : smallestPre.next;
            }
            if (smallestPre == null) {
                pre = head;
                head = head.next;
            } else {
                ListNode smallest = smallestPre.next;
                swapNode(head, pre, smallestPre.next, smallestPre);
                pre = smallest;
                head = pre.next;
            }

        }
        return ret;
    }

    public void swapNode(ListNode listNode1, ListNode preNode1, ListNode listNode2, ListNode preNode2) {
        if (listNode1.next == listNode2) {
            if (preNode1 != null) {
                preNode1.next = listNode2;
            }
            listNode1.next = listNode2.next;
            listNode2.next = listNode1;
        } else if (listNode2.next == listNode1) {
            if (preNode2 != null) {
                preNode2.next = listNode1;
            }
            listNode2.next = listNode1.next;
            listNode1.next = listNode2;
        } else {
            ListNode node1Next = listNode1.next;
            ListNode node2Next = listNode2.next;
            if (preNode1 != null) {
                preNode1.next = listNode2;
            }
            if (preNode2 != null) {
                preNode2.next = listNode1;
            }

            listNode2.next = node1Next;
            listNode1.next = node2Next;
        }
    }

    //寻找最小元素，并返回其前面的节点，一定是返回其前面的节点
    public ListNode getSmallPre(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode pre = null;
        ListNode smallest = head;
        while (head.next != null) {
            if (smallest.val > head.next.val) {
                smallest = head.next;
                pre = head;
            }
            head = head.next;
        }

        return pre;
    }
}
