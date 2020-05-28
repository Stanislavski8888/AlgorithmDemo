/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <br>
 * <b>注意:</b>
 * <ul>
 *     <li>每个数组中的元素不会超过 100 </li>
 *     <li>数组的大小不会超过 200</li>
 * </ul>
 * 示例 1: <br>
 * 输入: [1, 5, 11, 5] <br>
 * 输出: true <br>
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11]. <br>
 * <br>
 * <p>
 * 示例 2: <br>
 * 输入: [1, 2, 3, 5] <br>
 * 输出: false <br>
 * 解释: 数组不能分割成两个元素和相等的子集. <br>
 */
public class LeetCode416 {
    private static final int LENGTH = 11;
    private static final int MAX_ELEMENT = 20;

    public static void main(String[] args) {
        LeetCode416 leetcode = new LeetCode416();
        int[] numbers = new int[LENGTH];

        //随机生成weights和values，随后打印
        for (int i = 0; i < LENGTH; i++) {
            numbers[i] = (int) (Math.random() * MAX_ELEMENT + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            if (i != 0 && i % 10 == 0) {
                sb.append("\n");
            }
            sb.append(String.format(i == (LENGTH - 1) ? "%4d" : "%4d,", numbers[i]));
        }
        System.out.println(sb.toString());

        boolean canPartition = leetcode.partitionSubset(numbers);
        System.out.println("canPartition = " + canPartition);
    }

    /***
     * 定义一个一维的dp数组，其中dp[i]表示数字i是否是原数组的任意个子集合之和，那么我们我们最后只需要返回dp[target]就行了。<br>
     * 我们初始化dp[0]为true，由于题目中限制了所有数字为正数，那么我们就不用担心会出现和为0或者负数的情况。<br>
     * 那么关键问题就是要找出递归公式了，我们需要遍历原数组中的数字，对于遍历到的每个数字nums[i]，我们需要更新我们的dp数组，
     * 要更新[nums[i], target]之间的值，<br>那么对于这个区间中的任意一个数字j，如果dp[j - nums[i]]为true的话，那么dp[j]就一定为true，
     * 于是地推公式如下：
     * <br>
     * <b>
     * dp[j] = dp[j] || dp[j - nums[i]]         (nums[i] <= j <= target)
     * </b>
     * @param source 输入的正整数数组
     * @return true 可以分割成两个子集， false otherwise.
     */
    boolean partitionSubset(int[] source) {
        if (source.length <= 0 || source.length > 200) return false;

        int sum = 0;
        for (int s : source) {
            sum += s;
        }
        System.out.println("sum = " + sum);
        if (sum % 2 != 0) {
            System.out.println("Cann't partition!");
            return false;
        }

        int C = sum / 2;
        boolean[] memo = new boolean[C + 1];
        for (int i = 0; i <= C; i++) {
            memo[i] = source[0] == i;
        }

        for (int i = 1; i < source.length; i++) {
            for (int j = C; j >= source[i]; j--) {
                memo[j] = memo[j] || memo[j - source[i]];
            }
        }

        return memo[C];
    }
}
