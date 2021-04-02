import utils.Utils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode-cn.com/problems/largest-number/">179.最大数</a><br>
 * 给定一组非负整数 nums，重新排列它们每位数字的顺序使之组成一个最大的整数。
 * <br>
 * <strong>注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。</strong>
 * <br>
 *  
 * <pre>
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 * </pre>
 * <pre>
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * </pre>
 * <pre>
 * 示例 3：
 * 输入：nums = [1]
 * 输出："1"
 * </pre>
 * <pre>
 * 示例 4：
 * 输入：nums = [10]
 * 输出："10"
 * </pre> 
 * <pre>
 * <strong>提示：</strong>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 * </pre>
 */
public class LeetCode179 {
    static final int MAX = (int)(Math.random() * 1000);
    static final int LENGTH = (int)(Math.random() * 10 + 1);

    public static void main(String[] args) {
        System.out.println("MAX = " + MAX + ", LENGTH = " + LENGTH);
        int[] nums = Utils.genarateRandomArray(MAX, LENGTH);

        LeetCode179 instance = new LeetCode179();
        String result = instance.largestNumber(nums);
        System.out.println("result = " + result);
    }

    public String largestNumber(int[] nums) {
        // Get input integers as strings.
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // Sort strings according to custom comparator.
        Arrays.sort(asStrs, new LargerNumberComparator());

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        String largestNumberStr = new String();
        for (String numAsStr : asStrs) {
            largestNumberStr += numAsStr;
        }

        return largestNumberStr;
    }

    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

}