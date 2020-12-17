import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

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

    public static int[] genarateHalfOddEvenArray(int max, final int length) {
        int[] numbers = new int[length];
        int k = 0;
        int odd = 0;
        int even = 0;
        while (k < length) {
            numbers[k] = (int) (Math.random() * max + 1);
            if (numbers[k] % 2 == 0) {
                even++;
            } else {
                odd++;
            }
            if (even > length / 2) {
                even--;
                continue;
            }
            if (odd > length / 2) {
                odd--;
                continue;
            }
            k++;
        }

        StringBuilder sb = new StringBuilder("numbers = [");
        for (int i = 0; i < length; i++) {
            sb.append(numbers[i] + (i == (length - 1) ? "]" : ", "));
        }
        System.out.println(sb.toString());
        return numbers;
    }

    public static int[] genaratePositiveNegative(int max, final int length) {
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = (int) (Math.random() * max * (Math.random() > 0.5 ? 1 : -1));
        }

        StringBuilder sb = new StringBuilder("numbers = [");
        for (int i = 0; i < length; i++) {
            sb.append(numbers[i] + (i == (length - 1) ? "]" : ", "));
        }
        System.out.println(sb.toString());
        return numbers;
    }

    /**
     * 随机生成二维数组
     */
    public static int[][] genarateTwoDimenArray(int column, int row, int max) {
        int[][] twoDimen = new int[column][row];
        for (int i = 0; i < twoDimen.length; i++) {
            for (int j = 0; j < twoDimen[i].length; j++) {
                twoDimen[i][j] = (int)(Math.random() * max);
            }
        }
        return twoDimen;
    }

    public static String generateRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(26);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String[] generateSubsetString(int length) {
        String str = "123456789";
        if (length > str.length()) return null;

        CopyOnWriteArraySet<String> result = new CopyOnWriteArraySet<>();
        char s = str.charAt((int) (Math.random() * str.length()));
        while (result.size() < length) {
            result.add(String.valueOf(s));
            s = str.charAt((int) (Math.random() * str.length()));
        }
        String[] strArray = new String[result.size()];
        Arrays.sort(result.toArray(strArray));
        return strArray;
    }

    public static String generateEasyRepeatString(int length) {
        String str = "abcdefg";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(7);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static void printIntegerArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != (array.length - 1)) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    /**
     * 打印整型二维数组
     * @param digit 元素所占的字符长度
     */
    public static void printTwoDimenArray(int[][] array, int digit) {
        StringBuilder format = new StringBuilder();
        String formater = format.append("%").append(digit).append("d").toString();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf(formater, array[i][j]);
            }
            System.out.println();
        }
    }
}
