public class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int x) {
        this.val = x;
    }

    public ListNode() {
        // TODO Auto-generated constructor stub
    }

    public static ListNode genarateListNode(int length) {
        ListNode head = null; //new ListNode((int) (Math.random() * 20));
        ListNode temp = null;
        for (int i = 0; i < length; i++) {
            if (temp == null) {
                temp = new ListNode((int)(Math.random() * 20));
                head = temp;
            } else if (temp.next == null) {
                temp.next = new ListNode((int)(Math.random() * 20));
                temp = temp.next;
            }
        }

        printListNode(head);
        return head;
    }

    public static ListNode genarateSingleDigitNode(int length) {
        ListNode head = null; //new ListNode((int) (Math.random() * 20));
        ListNode temp = null;
        for (int i = 0; i < length; i++) {
            if (temp == null) {
                temp = new ListNode((int)(Math.random() * 10));
                head = temp;
            } else if (temp.next == null) {
                temp.next = new ListNode((int)(Math.random() * 10));
                temp = temp.next;
            }
        }

        printListNode(head);
        return head;
    }

    public static void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.print("null");
        System.out.println();
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }

}
