/**
 * <a href="https://leetcode-cn.com/problems/three-steps-problem-lcci/">面试题 08.01. 三步问题</a>
 * <p>
 * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。<p>
 * 结果可能很大，你需要对结果模1000000007。
 * <p>
 * <strong>示例1:</strong>
 * <pre>
 * <strong>输入：</strong>n = 3
 * <strong>输出：</strong>4
 * <strong>说明:</strong> 有四种走法
 * </pre>
 * <strong>示例2:</strong>
 * <pre>
 * <strong>输入：</strong>n = 5
 * <strong>输出：</strong>13
 * </pre>
 * <strong>提示:</strong>
 * <pre>
 * n范围在[1, 1000000]之间
 * </pre>
 */
public class LeetCodeThreeSteps {

    public static void main(String[] args) {
        LeetCodeThreeSteps instance = new LeetCodeThreeSteps();
        int N = (int)(Math.random() * 100 + 1);
        System.out.println("N = " + N);
        long ways = instance.waysToStep(N);
        System.out.println("ways = " + ways);
    }

    public long waysToStep(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        long[] ways = new long[n];
        ways[0] = 1;
        ways[1] = 2;
        ways[2] = 4;
        for (int i = 3; i < n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2] + ways[i - 3];
        }

        return ways[n - 1];
    }
}
