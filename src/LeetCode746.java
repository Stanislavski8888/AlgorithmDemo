import utils.Utils;

/**
 * <a href="https://leetcode-cn.com/problems/min-cost-climbing-stairs/">746. 使用最小花费爬楼梯</a><br>
 * <p>
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。<br>
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。<br>
 * <p>
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。<br>
 *
 * <strong>示例 1:</strong>
 * <pre>
 * <strong>输入:</strong> cost = [10, 15, 20]
 * <strong>输出:</strong> 15
 * <strong>解释:</strong> 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 * </pre>
 * <strong>示例 2:</strong>
 * <pre>
 * <strong>输入:</strong> cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * <strong>输出:</strong> 6
 * <strong>解释:</strong> 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 * </pre>
 * <strong>注意：</strong>
 * <pre>
 * cost 的长度将会在 [2, 1000]。
 * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
 * </pre>
 */
public class LeetCode746 {
    static final int LENGTH = (int) (Math.random() * 20 + 1);
    static final int MAX = (int) (Math.random() * 20);

    public static void main(String[] args) {
        int[] cost = Utils.genarateRandomArray(MAX, LENGTH);
        LeetCode746 instance = new LeetCode746();
        int minCost = instance.minCostClimbingStairs(cost);
        System.out.println("minCost = " + minCost);
    }

    public int minCostClimbingStairs(int[] cost) {
        if (cost.length < 2) return 0;
        if (cost.length == 2) return Math.min(cost[0], cost[1]);
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            if (i == cost.length - 1) {
                dp[i] = Math.min(dp[i - 1], dp[i - 2] + cost[i]);
            } else {
                dp[i] = Math.min(dp[i - 1] + cost[i], dp[i - 2] + cost[i]);
            }
        }
        return dp[cost.length - 1];
    }

    /**
     * <strong>方法：动态规划</strong><br>
     *
     * 计算花费 f[i] 有一个清楚的递归关系：f[i] = cost[i] + min(f[i+1], f[i+2])。我们可以使用动态规划来实现。
     * <p>
     * <strong>算法：</strong><br>
     * <pre>
     * 当我们要计算 f[i] 时，要先计算出 f[i+1] 和 f[i+2]。所以我们应该从后往前计算 f。
     * 在第 i 步，让 f1，f2 为 f[i+1]，f[i+2] 的旧值，并将其更新为f[i]，f[i+1] 的新值。当我们从后遍历 i 时，我们会保持这些更新。在最后答案是 min(f1, f2)。
     * </pre>
     */
    public int minCostClimbingStairsOfficial(int[] cost) {
        int f1 = 0, f2 = 0;
        for (int i = cost.length - 1; i >= 0; --i) {
            int f0 = cost[i] + Math.min(f1, f2);
            f2 = f1;
            f1 = f0;
        }
        return Math.min(f1, f2);
    }

}