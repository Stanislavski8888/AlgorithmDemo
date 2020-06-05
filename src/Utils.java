public class Utils {
    public static int[] genarateRandomArray(int max, final int length) {
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = (int) (Math.random() * max + 1);
        }

        StringBuilder sb = new StringBuilder("numbers = [");
        for (int i = 0; i < length; i++) {
            sb.append(numbers[i] + (i == (length - 1) ? "]" : ", "));
        }
        System.out.println(sb.toString());
        return numbers;
    }
}
