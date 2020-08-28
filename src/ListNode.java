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

    public static void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.print("null");
        System.out.println();
    }
}
