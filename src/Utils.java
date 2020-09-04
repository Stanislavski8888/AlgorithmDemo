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
