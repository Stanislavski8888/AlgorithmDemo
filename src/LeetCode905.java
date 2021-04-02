import utils.Utils;

/**
 * <a href="https://leetcode-cn.com/problems/sort-array-by-parity/">905. 按奇偶排序数组</a><br>
 * 给定一个非负整数数组 A，返回一个数组，在该数组中，A 的所有偶数元素之后跟着所有奇数元素。
 * <br>
 * 你可以返回满足此条件的任何数组作为答案。
 * <br>
 *
 * <pre>
 * 示例：
 *
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 * </pre>
 * <pre>
 * 提示：
 *
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 * </pre>
 */
public class LeetCode905 {
    static final int MAX = 20;
    static final int LENGTH = (int)(Math.random() * 20 + 1);
    public static void main(String[] args) {
        LeetCode905 instance = new LeetCode905();
        int[] array = Utils.genarateRandomArray(MAX, LENGTH);
//        int[] result = instance.sortArrayByParity(array);
        int[] result = instance.sortArrayByParity2(array);
        Utils.printIntegerArray(result);
    }

    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length <= 0) {
            return null;
        }
        int length = A.length;
        for(int i = 0; i < length; i++) {
            int j = i + 1;
            if (A[i] % 2 == 1) {
                while (j < length && A[j] % 2 == 1) {
                    j++;
                }
                if (j < length) {
                    swap(A, i, j);
                }
            }
        }
        return A;
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public int[] sortArrayByParity2(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            if (A[i] % 2 > A[j] % 2) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

            if (A[i] % 2 == 0) i++;
            if (A[j] % 2 == 1) j--;
        }

        return A;
    }

}