/**
 * <a href="https://leetcode-cn.com/problems/predict-the-winner/">486. 预测赢家</a>
 * <p>
 * 给定一个表示分数的非负整数数组。 <br>
 * 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。<br>
 * 每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。<br>
 * 直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
 * <br>
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。<br>
 * 你可以假设每个玩家的玩法都会使他的分数最大化。
 * <br>
 *
 * <pre>
 * <strong>示例 1：</strong>
 * <strong>输入：</strong>[1, 5, 2]
 * <strong>输出：</strong>False
 * <strong>解释：</strong>一开始，玩家1可以从1和2中进行选择。
 *      如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
 *      所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
 *      因此，玩家 1 永远不会成为赢家，返回 False 。
 * </pre>
 * <pre>
 * <strong>示例 2：</strong>
 * <strong>输入：</strong>[1, 5, 233, 7]
 * <strong>输出：</strong>True
 * <strong>解释：</strong>玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
 *      最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
 * </pre>
 *
 * <pre>
 * <strong>提示：</strong>
 *     1 <= 给定的数组长度 <= 20.
 *     数组里所有分数都为非负数且不会大于 10000000 。
 *     如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。
 * </pre>
 */
public class LeetCode486 {
    static final int MAX = 1000;
    static final int LENGTH = (int) (Math.random() * 20 + 1);

    public static void main(String[] args) {
        System.out.println("length = " + LENGTH);
        int[] numbers = Utils.genarateRandomArray(MAX, LENGTH);
        LeetCode486 instance = new LeetCode486();
//        boolean win = instance.PredictTheWinner(numbers);
        boolean win = instance.PredictTheWinnerOfficial(numbers);
        System.out.println("win = " + win);
    }

    public boolean PredictTheWinner(int[] nums) {
        int player1 = 0;
        int player2 = 0;
        int head = 0;
        int tail = nums.length - 1;
        int count = 0;
        int who;
        while (count < nums.length) {
            boolean isPlayer1 = count % 2 == 0;
            who = isPlayer1 ? player1 : player2;
            if (nums[head] > nums[tail]) {
                who += nums[head];
                head++;
            } else {
                who += nums[tail];
                tail--;
            }
            if (isPlayer1) {
                player1 = who;
            } else {
                player2 = who;
            }
            count++;
        }
        System.out.println("player1 = " + player1 + ", player2 = " + player2);
        return player1 >= player2;
    }

    /**
     * 方法一:递归.
     * <pre>
     * <strong>复杂度分析</strong>
     * 时间复杂度：O(2^n)，其中 n 是数组的长度。
     * 空间复杂度：O(n)，其中 n 是数组的长度。空间复杂度取决于递归使用的栈空间。
     * </pre>
     */
    public boolean PredictTheWinnerRecursive(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }

    public int total(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }
        int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
        int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
        return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
    }


    /**
     * 方法二:动态规划.注释中的代码未做空间优化
     * @param nums
     * @return
     */
    public boolean PredictTheWinnerOfficial(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length][length];
//        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
//            dp[i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
//                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        System.out.println("Matrix:");
        Utils.printTwoDimenArray(dp, 6);
        return dp[0][length - 1] >= 0;
//        return dp[length - 1] >= 0;
    }

}
