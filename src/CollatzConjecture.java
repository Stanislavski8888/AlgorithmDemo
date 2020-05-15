public class CollatzConjecture {
    public static void main(String[] args) {
        long num = (long) (Math.random() * Long.MAX_VALUE);
        System.out.println("num = " + num);

        CollatzConjecture cc = new CollatzConjecture();
        cc.function(num);
    }

    private void function(long num) {
        if (num <= 0) {
            return;
        }
        while (num != 1) {
            long n = num % 2;
            if (n == 0) {
                num /= 2;
            } else {
                num = num * 3 + 1;
            }
            System.out.println("num = " + num);
        }
    }
}
