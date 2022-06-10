import java.util.Iterator;
import java.util.LinkedList;

public class Josephus {
    private static final int MAX = 30;
    private static final int STEP = 4;

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList();
        for(int i = 0; i < MAX; i++) {
            list.add(i + 1);
        }
        for(int i : list) {
            System.out.print(i + "->");
        }
        System.out.println();
        jose(list, STEP);
    }

    private static void jose(LinkedList<Integer> list, int step) {
        Iterator<Integer> iterator = list.iterator();
        for (int i = 1; iterator.hasNext(); iterator.next(), i++) {
            if (i == step) {
                int aim = iterator.next();
                System.out.print(aim + "->");
                iterator.remove();
                i = 1;
            }
        }
    }
}