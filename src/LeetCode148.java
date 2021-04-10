/**
 * <a href="https://leetcode-cn.com/problems/sort-list/">148. 排序链表</a>
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <br>
 * 进阶：
 * <br>
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * <br>
 * <pre>
 * 示例 1：
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * </pre>
 * <pre>
 * 示例 2：
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * </pre>
 * <pre>
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 * </pre>
 */
public class LeetCode148 {
    static final int LENGTH = 20;

    public static void main(String[] args) {
        ListNode head = ListNode.genarateListNode(LENGTH);
        LeetCode148 instance = new LeetCode148();

        ListNode result = instance.sortList(head);
        ListNode.printListNode(result);

        result = instance.sortListOfficial(head);
        ListNode.printListNode(result);
    }

    // 插入排序
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode result = null;
        ListNode current = null;
        while (head != null) {
            if (result == null) {
                result = new ListNode(head.val);
                current = result;
            } else {
                ListNode pre = current;
                while (current != null) {
                    if (current.val < head.val) {
                        pre = current;
                        current = current.next;
                    } else {
                        break;
                    }
                }
                ListNode tmp = new ListNode(head.val, current);
                if (result == current) {
                    current = tmp;
                    result = current;
                } else {
                    pre.next = tmp;
                    current = result;
                }
            }
            head = head.next;
        }

        return result;
    }

    // 插入排序官方方法
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }


    // 归并排序
    public ListNode sortListOfficial(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

}


