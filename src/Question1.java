/**
 * 请用任意语言实现从一个整形数组中查找最大和第二大的数，输出最大的数和其位置，第二大数和其位置。<br>
 * 要求时间复杂度不可大于或等于O(nlogn)。分析您写的代码的时空复杂度。
 * <br>
 * <pre>
 * 举例1
 * 输入：4 3 2 1 0
 * 输出：4 0 3 1
 * </pre>
 * <pre>
 * 举例2
 * 输入：2 7 8 1 3 4
 * 输出：8 2 7 1
 * </pre>
 */
public class Question1 {
    public static void main(String[] args) {

        Question1 instance = new Question1();
        int[] array = Utils.genarateRandomArray(20, 3);
        int[] result = instance.findMaxAndSecond(array);
        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    public int[] findMaxAndSecond(int[] array) {
        if (array == null || array.length < 2) {
            return null;
        }
        int[] result = new int[4];
        int max = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int maxIndex = -1;
        int secondIndex = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= max) {
                if (max > second) {
                    second = max;
                    secondIndex = maxIndex;
                }
                max = array[i];
                maxIndex = i;
            } else if (array[i] >= second && array[i] < max){
                second = array[i];
                secondIndex = i;
            }
        }
        result[0] = max;
        result[1] = maxIndex;
        result[2] = second;
        result[3] = secondIndex;

        return result;
    }
}
