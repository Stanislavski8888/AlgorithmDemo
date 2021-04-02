import utils.Utils;

/**
 * <a href="https://leetcode-cn.com/problems/house-robber">198. 打家劫舍</a>
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。<p>
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，<p>
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你<strong>不触动警报装置的情况下</strong>，一夜之内能够偷窃到的最高金额。
 * <p>
 * <strong>示例 1：
 * <pre>
 * <strong>输入：[1,2,3,1]
 * <strong>输出：4
 * <strong>解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * </pre>
 * <p>
 * <strong>示例 2：
 * <pre>
 * <strong>输入：[2,7,9,3,1]
 * <strong>输出：12
 * <strong>解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * </pre>
 * <p>
 * <strong>提示：</strong>
 * <pre>
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * </pre>
 */
public class LeetCode198 {
    static final int LENGTH = (int) (Math.random() * 20 + 1);
    static final int MAX = (int) (Math.random() * 20);
    public static void main(String[] args) {
        int[] nums = Utils.genarateRandomArray(MAX, LENGTH);
//        nums = new int[]{1, 2, 100, 1, 2, 200, 1, 2};
        LeetCode198 instance = new LeetCode198();
        int max = instance.rob(nums);
        System.out.println("max = " + max);

    }

    /**
     * <strong>方法一：动态规划 + 滚动数组</strong><br>
     *
     * 首先考虑最简单的情况。如果只有一间房屋，则偷窃该房屋，可以偷窃到最高总金额。<p>
     * 如果只有两间房屋，则由于两间房屋相邻，不能同时偷窃，只能偷窃其中的一间房屋，因此选择其中金额较高的房屋进行偷窃，可以偷窃到最高总金额。<br>
     *
     * 如果房屋数量大于两间，应该如何计算能够偷窃到的最高总金额呢？对于第 k (k>2) 间房屋，有两个选项：<br>
     * <ol>
     *     <li>偷窃第 k 间房屋，那么就不能偷窃第 k−1 间房屋，偷窃总金额为前 k−2 间房屋的最高总金额与第 k 间房屋的金额之和。</li>
     *
     *     <li>不偷窃第 k 间房屋，偷窃总金额为前 k−1 间房屋的最高总金额。</li>
     * </ol>
     * 在两个选项中选择偷窃总金额较大的选项，该选项对应的偷窃总金额即为前 k 间房屋能偷窃到的最高总金额。<br>
     *
     * 用 dp[i] 表示前 i 间房屋能偷窃到的最高总金额，那么就有如下的状态转移方程：<br>
     *
     * <strong>dp[i]=max⁡(dp[i−2]+nums[i],dp[i−1])</strong>
     * <p>
     * 边界条件为：
     * <ol>
     * <li>dp[0]=nums[0]只有一间房屋，则偷窃该房屋</li>
     * <li>dp[1]=max⁡(nums[0],nums[1])只有两间房屋，选择其中金额较高的房屋进行偷窃</li>
     * </ol>
     * 最终的答案即为 dp[n−1]，其中 n 是数组的长度。
     *
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    public int robOpt(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

}