/**
 * <a href="https://leetcode-cn.com/problems/contiguous-sequence-lcci">面试题 16.17. 连续数列</a>
 * <p>
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。<p>
 * <strong>示例：</strong>
 * <pre>
 * <strong>输入：</strong> [-2,1,-3,4,-1,2,1,-5,4]
 * <strong>输出：</strong> 6
 * <strong>解释：</strong> 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * </pre>
 * <strong>进阶：</strong><p>
 * <pre>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * </pre>
 */
public class LeetCodeContiguousSequence {
    static final int LENGTH = (int) (Math.random() * 20 + 1);
    static final int MAX = (int) (Math.random() * 20);

    public static void main(String[] args) {
        int[] nums = Utils.genaratePositiveNegative(MAX, LENGTH);
        LeetCodeContiguousSequence instance = new LeetCodeContiguousSequence();
        instance.maxSubArray(nums);
    }

    /**
     * <strong>动态规划解析：</strong>
     * <p>
     * <strong>状态定义：</strong>
     * <pre>
     * 设动态规划列表 dp ，dp[i] 代表以元素 nums[i] 为结尾的连续子数组最大和。
     * 为何定义最大和 dp[i] 中必须包含元素 nums[i] ：保证 dp[i] 递推到 dp[i+1] 的正确性；如果不包含 nums[i] ，递推时则不满足题目的 连续子数组 要求。
     * </pre>
     * <p>
     * <strong>转移方程：</strong>
     * <pre>
     * 若 dp[i−1]≤0 ，说明 dp[i−1] 对 dp[i] 产生负贡献，即 dp[i−1]+nums[i] 还不如 nums[i] 本身大。
     * 当 dp[i−1]>0 时：执行 dp[i]=dp[i−1]+nums[i] ；
     * 当 dp[i−1]≤0 时：执行 dp[i]=nums[i] ；
     * </pre>
     * <p>
     * <strong>初始状态：</strong>
     * <pre>
     * dp[0]=nums[0]，即以 nums[0] 结尾的连续子数组最大和为 nums[0] 。
     * </pre>
     * <p>
     * <strong>返回值：</strong>
     * <pre>
     * 返回 dp 列表中的最大值，代表全局最大值。
     * </pre>
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        int[] dp = new int[len];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        System.out.println("max = " + max);
        return max;
    }
}
