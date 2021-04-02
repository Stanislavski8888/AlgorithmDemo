import utils.Utils;

/**
 * <a href="https://leetcode-cn.com/problems/sort-array-by-parity-ii/"></a>922. 按奇偶排序数组 II<br>
 * 给定一个非负整数数组A， A 中一半整数是奇数，一半整数是偶数。
 * <br>
 * 对数组进行排序，以便当A[i] 为奇数时，i也是奇数；当A[i]为偶数时， i 也是偶数。
 * <br>
 * 你可以返回任何满足上述条件的数组作为答案。
 * <br>
 *
 * <pre>
 * 示例：
 *
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 * </pre>
 * <pre>
 * 提示：
 *
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 * </pre>
 */
public class LeetCode922 {
    static final int MAX = 20;
    static final int LENGTH = 16;
    public static void main(String[] args) {
        LeetCode922 instance = new LeetCode922();
        int[] array = Utils.genarateHalfOddEvenArray(MAX, LENGTH);
//        int[] result = instance.sortArrayByParityII(array);
        int[] result = instance.sortArrayByParityIII(array);
        Utils.printIntegerArray(result);
    }

    public int[] sortArrayByParityII(int[] A) {
        if (A == null || A.length <= 0) {
            return null;
        }
        int length = A.length;
        int[] res = new int[length];
        int i = 0;
        for (int a : A) {
            if (a % 2 == 0) {
                res[i] = a;
                i += 2;
            }
        }
        i = 1;
        for (int a : A) {
            if (a % 2 == 1) {
                res[i] = a;
                i += 2;
            }
        }
        return res;
    }

    public int[] sortArrayByParityIII(int[] A) {
        if (A == null || A.length <= 0) {
            return null;
        }
        int length = A.length;
        int j = 1;
        for (int i = 0; i < length; i += 2) {
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}