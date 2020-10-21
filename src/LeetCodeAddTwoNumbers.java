/**
 * <a href="https://leetcode-cn.com/problems/add-two-numbers/">2.两数相加</a><br>
 * 给出两个 <strong>非空</strong> 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 <strong>逆序</strong> 的方式存储的，并且它们的每个节点只能存储 <strong>一位</strong> 数字。
 * <br>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <br>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <br>
 * <strong>示例：</strong>
 * <pre>
 * <strong>输入：</strong>(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * <strong>输出：</strong>7 -> 0 -> 8
 * <strong>原因：</strong>342 + 465 = 807
 * </pre>
 */
public class LeetCodeAddTwoNumbers {
    public static void main(String[] args) {
        ListNode listNode1 = ListNode.genarateSingleDigitNode((int)(Math.random() * 5 + 1));
        ListNode listNode2 = ListNode.genarateSingleDigitNode((int)(Math.random() * 5 + 1));

        LeetCodeAddTwoNumbers instance = new LeetCodeAddTwoNumbers();
//        ListNode result = instance.addTwoNumbers(listNode1, listNode2);
        ListNode result = instance.addTwoNumbersOfficial(listNode1, listNode2);
        ListNode.printListNode(result);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode temp = null;
        boolean carry = false;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            if (carry) {
                sum++;
            }
            if (temp == null) {
                temp = new ListNode();
                result = temp;
            } else if (temp.next == null) {
                temp.next = new ListNode();
                temp = temp.next;
            }
            if (sum < 10) {
                temp.val = sum;
                carry = false;
            } else {
                temp.val = sum - 10;
                carry = true;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            if (temp.next == null) {
                temp.next = new ListNode();
                temp = temp.next;
            }
            int sum = carry ? l1.val + 1 : l1.val;
            if (sum < 10) {
                temp.val = sum;
                carry = false;
            } else {
                temp.val = sum - 10;
                carry = true;
            }
            l1 = l1.next;
        }
        while (l2 != null) {
            if (temp.next == null) {
                temp.next = new ListNode();
                temp = temp.next;
            }
            int sum = carry ? l2.val + 1 : l2.val;
            if (sum < 10) {
                temp.val = sum;
                carry = false;
            } else {
                temp.val = sum - 10;
                carry = true;
            }
            l2 = l2.next;
        }
        if (carry) {
            if (temp.next == null) {
                temp.next = new ListNode();
                temp = temp.next;
            }
            temp.val = 1;
        }
        return result;
    }

    public ListNode addTwoNumbersOfficial(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

}
