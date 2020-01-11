/*Definition for singly-linked list.*/
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}

public class ReverseListSolution {
    // 我的方法
    public ListNode reverseListMine(ListNode head) {
        if (head == null) return null;
        if (head.next == null) {
            return head;
        }
        ListNode newHead = null;
        while (head.next != null) {
            ListNode temp = new ListNode(head.val);
            temp.next = newHead;
            newHead = temp;
            head = head.next;
        }
        ListNode temp = new ListNode(head.val);
        temp.next = newHead;
        newHead = temp;
        return newHead;
    }

    // 递归
    public ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // 迭代
    public ListNode reverseListIteration(ListNode head) {
        ListNode newList = new ListNode(-1);
        while(head != null){
            ListNode next = head.next;
            head.next = newList.next;
            newList.next = head;
            head = next;
        }
        return newList.next;
    }
}


