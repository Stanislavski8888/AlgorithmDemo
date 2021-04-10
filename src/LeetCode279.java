import java.util.HashMap;

/**
 * <a href="https://leetcode-cn.com/problems/perfect-squares/">279. 完全平方数</a><br>
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <br>
 * <pre>
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * </pre>
 * <pre>
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 * </pre>
 */
public class LeetCode279 {
    static final int N = (int) (Math.random() * Integer.MAX_VALUE);

    public static void main(String[] args) {
        System.out.println("N = " + N + ", sqrt = " + (int)Math.floor(Math.sqrt(N)));
        LeetCode279 instance = new LeetCode279();
        int result = instance.numSquares(30);
        System.out.println("result = " + result);
    }

    public int numSquares(int n) {
        if (n < 1) return 0;
        int maxSquare = (int) Math.pow(Math.floor(Math.sqrt(n)), 2);
        if (maxSquare == n) {
            return 1;
        }
        HashMap<Integer, Integer> assemble = new HashMap<>();
        assemble.put(maxSquare, 1);
        int preSquare = (int) Math.pow(Math.sqrt(maxSquare) - 1, 2);
//        int ret = Math.min(numSquares(n - maxSquare), numSquares(n - preSquare));
        return 0;
    }
}
