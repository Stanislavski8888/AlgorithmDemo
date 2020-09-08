/**
 * <a href="https://leetcode-cn.com/problems/numbers-at-most-n-given-digit-set/">902. 最大为 N 的数字组合</a>
 * <p>
 * 我们有一组排序的数字 D，它是  {'1','2','3','4','5','6','7','8','9'} 的非空子集。<br>
 * （请注意，'0' 不包括在内。）<br>
 * <p>
 * 现在，我们用这些数字进行组合写数字，想用多少次就用多少次。<br>
 * 例如 D = {'1','3','5'}，我们可以写出像 '13', '551', '1351315' 这样的数字。
 * <br>
 * 返回可以用 D 中的数字写出的小于或等于 N 的正整数的数目。<br>
 *
 * <pre>
 * <strong>示例 1：</strong>
 * <strong>输入：</strong>D = ["1","3","5","7"], N = 100
 * <strong>输出：</strong>20
 * <strong>解释：</strong>
 * 可写出的 20 个数字是：
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 * </pre>
 * <pre>
 * <strong>示例 2：</strong>
 * <strong>输入：</strong>D = ["1","4","9"], N = 1000000000
 * <strong>输出：</strong>29523
 * <strong>解释：</strong>
 * 我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
 * 81 个四位数字，243 个五位数字，729 个六位数字，
 * 2187 个七位数字，6561 个八位数字和 19683 个九位数字。
 * 总共，可以使用D中的数字写出 29523 个整数。
 * </pre>
 *
 * <strong>提示：</strong>
 * <ol>
 *     <li>D 是按排序顺序的数字 '1'-'9' 的子集。</li>
 *     <li>1 <= N <= 10^9</li>
 * </ol>
 */
public class LeetCode902 {
    static int LENGTH = (int) (Math.random() * 10);
    static final int N = (int) (Math.random() * Math.pow(10, 9));

    public static void main(String[] args) {
        while (LENGTH <= 0) {
            LENGTH = (int) (Math.random() * 10);
        }
        System.out.println("N = " + N);
        System.out.println("LENGTH = " + LENGTH);
        String[] D = Utils.generateSubsetString(LENGTH);
        for (String s : D) {
            System.out.printf("%3s", s);
        }
        System.out.println();

        LeetCode902 instance = new LeetCode902();
        int result = instance.atMostNGivenDigitSetOfficial(D, N);
        System.out.println("result = " + result);
    }

    public int atMostNGivenDigitSetOfficial(String[] D, int N) {
        int B = D.length; // bijective-base B
        char[] ca = String.valueOf(N).toCharArray();
        int K = ca.length;
        int[] A = new int[K];
        int t = 0;

        for (char c : ca) {
            int c_index = 0;  // Largest such that c >= D[c_index - 1]
            boolean match = false;
            for (int i = 0; i < B; ++i) {
                if (c >= D[i].charAt(0))
                    c_index = i + 1;
                if (c == D[i].charAt(0))
                    match = true;
            }

            A[t++] = c_index;
            if (match) continue;

            if (c_index == 0) { // subtract 1
                for (int j = t - 1; j > 0; --j) {
                    if (A[j] > 0) break;
                    A[j] += B;
                    A[j - 1]--;
                }
            }

            while (t < K)
                A[t++] = B;
            break;
        }

        int ans = 0;
        for (int x : A)
            ans = ans * B + x;
        return ans;
    }

    /**
     * 动态规划
     */
    public int atMostNGivenDigitSet(String[] D, int N) {
        String S = String.valueOf(N);
        int K = S.length();
        int[] dp = new int[K + 1];
        dp[K] = 1;

        for (int i = K - 1; i >= 0; --i) {
            // compute dp[i]
            int Si = S.charAt(i) - '0';
            for (String d : D) {
                if (Integer.valueOf(d) < Si)
                    dp[i] += Math.pow(D.length, K - i - 1);
                else if (Integer.valueOf(d) == Si)
                    dp[i] += dp[i + 1];
            }
        }

        for (int i = 1; i < K; ++i)
            dp[0] += Math.pow(D.length, i);
        return dp[0];
    }
}
