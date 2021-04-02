import utils.ListNode;

/**
 * <a href="https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/">剑指 Offer 18. 删除链表的节点</a><br>
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * <br>
 * 返回删除后的链表的头节点。
 * <br>
 * 注意：此题对比原题有改动
 * <pre>
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * </pre>
 * <pre>
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 * </pre> 
 * <pre>
 * 说明：
 *
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 * </pre>
 */
public class LeetCodeDeleteNode {
    public static void main(String[] args) {
        int length = (int) (Math.random() * 20 + 1);
        int value = (int) (Math.random() * 20);
        System.out.println("length = " + length + ", value = " + value);
        ListNode head = ListNode.genarateListNode(length);

        LeetCodeDeleteNode instance = new LeetCodeDeleteNode();
        ListNode result = instance.deleteNode(head, value);
        System.out.println("Result:");
        ListNode.printListNode(result);
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        ListNode tmp = head;
        ListNode pre = null;
        while (tmp != null) {
            if (tmp.val == val) {
                if (pre == null) {
                    return tmp.next;
                } else {
                    pre.next = tmp.next;
//                    tmp = null;
                }
                break;
            }
            pre = tmp;
            tmp = tmp.next;
        }

        return head;
    }
}