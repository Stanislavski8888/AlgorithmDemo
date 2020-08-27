import java.util.Stack;

/**
 * <p>
 * 有一个单链表，请设计一个算法，使得每K个节点之间逆序，如果最后不够K个节点一组，则不调整最后几个节点。
 * <p>
 * 例如链表1->2->3->4->5->6->7->8->null，K=3这个例子。 调整后为，3->2->1->6->5->4->7->8->null。
 * <p>
 * 因为K==3，所以每三个节点之间逆序，但其中的7，8不调整，因为只有两个节点不够一组。
 * <p>
 * 给定一个单链表的头指针head,同时给定K值，返回逆序后的链表的头指针。
 */
public class LinkNode {
    private static final int K = 4;
    private static final int LENGTH = 10;

    public static void main(String[] args) {
        ListNode test = new ListNode(0);
        ListNode cur = test;
        for (int i = 1; i < LENGTH; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        ListNode read = new ListNode();
//        read = inverse(test, 6);
        read = inverseByStack(test, K);
        while (read != null) {
            System.out.print(read.val+"->");
            read = read.next;
        }
    }

    /*public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        public ListNode() {
            // TODO Auto-generated constructor stub
        }
    }*/

    /**
     * 方法一：利用变量存储，每k个节点之间逆序。
     */
    static ListNode inverse(ListNode head, int k) {
        if (k < 2)
            return head;
        ListNode cur = head;
        ListNode pre = null;
        ListNode start = null;
        ListNode next = null;
        int count = 1;
        while (cur != null) {
            next = cur.next;
            if (count == k) {
                start = pre == null ? head : pre.next;
                // 第一次便可以确定头结点，以后头结点保持不变
                head = pre == null ? cur : head;
                // 将需要翻转的区间使用pre和right封闭。对区间内部的进行完全链表翻转
                // 然后将头结点和尾结点与pre和right相连接，保持链表的连续性.
                reverse(pre, start, cur, cur.next);
                // 翻转后的头结点将变成尾结点，作为下一次的pre
                pre = start;
                // 基数器清零
                count = 0;
            }
            cur = next;
            count++;
        }
        return head;
    }

    static void reverse(ListNode left, ListNode start, ListNode end, ListNode right) {
        ListNode cur = start;
        ListNode pre = left;
        ListNode next = null;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            // 同步向后移动
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;
        }
        start.next = right;
    }
    
    
    /**
     * 方法二：利用栈结构，每k个节点之间逆序
     */
     public static ListNode inverseByStack(ListNode head, int K) {
         if (K < 2) 
         {
             return head;
         }
         Stack<ListNode> stack = new Stack<ListNode>();
         ListNode newHead = head; //记录新的头结点
         ListNode cur = head;
         ListNode pre = null;
         ListNode next = null;
         while (cur != null) {
             next = cur.next;
             stack.push(cur);
             if (stack.size() == K) {
                 pre = resignByStack(stack, pre, next);
                 newHead = newHead == head ? cur : newHead;
             }
             cur = next;
         }
         return newHead;
     }
     
     //调整k个节点逆序
     public static ListNode resignByStack(Stack<ListNode> stack, ListNode left, ListNode right) {
         ListNode cur = stack.pop();
         if (left != null) {
             left.next = cur;
         }
         ListNode next = null;
         while (!stack.isEmpty()) {
             next = stack.pop();
             cur.next = next;
             cur = next;
         }
         cur.next = right;
         return cur;
     }
     
    /**
     * 方法三：反转部分链表法。每k个节点之间逆序
     */
    public static ListNode inversePortion(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        if (k == 1) {
            return head;
        }
        int n = 0; // 记录链经历的节点数

        ListNode q = head;  // 记录链表的头节点
        int leng = 0;
        while (q != null) {
            ++leng;
            q = q.next;
        }
        // n=(int)Math.floor(leng/k);
        n = leng / k; // 记录反转的次数
        // System.out.println(n);
        int from = 1;
        int to = k;
        while (n > 0) {
            ListNode mode = reverseSublist(head, from, to); // 记录每次变换后的链表头节点
            // PrintList(mode);
            head = mode;
            from += k;
            to += k;
            n--;
        }
        return head;
    }

    // 反转部分链表
    public static ListNode reverseSublist(ListNode head, int from, int to) {
        if (head == null || from < 0 || to < 0) {
            return head;
        }
        ListNode p = head;
        int leng = 0;
        ListNode tPre = null; // 获取反转起始节点的前一个节点
        ListNode tPos = null; // 获取反转结束节点的后一个节点
        // 计算链表长度
        while (p != null) {
            ++leng;
            tPre = (leng == from - 1 ? p : tPre); // 找到反转起始节点的前一个节点
            tPos = (leng == to + 1 ? p : tPos); // 找到反转结束节点的后一个节点
            p = p.next;
        }
        if (from > leng || to > leng || from > to) {
            return head;
        }
        p = tPre == null ? head : tPre.next; // 找到要起始反转的节点
        ListNode node2 = p.next;
        p.next = tPos; // 起始反转的节点指向反转结束节点的后一个节点

        ListNode next = null;
        while (node2 != tPos) {
            next = node2.next;
            node2.next = p;
            p = node2;
            node2 = next;
        }
        if (tPre != null) {
            tPre.next = p;
            return head;
        }

        return p;
    }

}
