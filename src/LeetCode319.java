/**
 * <a href="https://leetcode-cn.com/problems/bulb-switcher/">319. 灯泡开关</a>
 * <p>
 * 初始时有 n 个灯泡关闭。<p>
 * 第 1 轮，你打开所有的灯泡。<p>
 * 第 2 轮，每两个灯泡你关闭一次。<p>
 * 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。<p>
 * 第 i 轮，每 i 个灯泡切换一次开关。<p>
 * 对于第 n 轮，你只切换最后一个灯泡的开关。<p>
 * 找出 n 轮后有多少个亮着的灯泡。<p>
 * <b>示例:</b>
 * <div>
 * <strong>输入:</strong> 3 <p>
 * <strong>输出:</strong> 1 <p>
 * <strong>解释:</strong>
 * <ul>
 *  <li>初始时, 灯泡状态 [关闭, 关闭, 关闭].</li>
 *  <li>第一轮后, 灯泡状态 [开启, 开启, 开启].</li>
 *  <li>第二轮后, 灯泡状态 [开启, 关闭, 开启].</li>
 *  <li>第三轮后, 灯泡状态 [开启, 关闭, 关闭].</li>
 * </ul>
 * 你应该返回 1，因为只有一个灯泡还亮着。
 * </div>
 */
public class LeetCode319 {
    static int MAX = (int) (Math.random() * Integer.MAX_VALUE);

    public static void main(String[] args) {
        LeetCode319 instance = new LeetCode319();
        System.out.println("MAX = " + MAX);
//        int sum = instance.bulbSwitch(MAX);
//        System.out.println("sum = " + sum);
        int sum0 = instance.bulbSwitchOpt(MAX);
        System.out.println("sum0 = " + sum0);
        System.out.println("Math.sqrt(" + MAX + ") = " + (int)Math.sqrt(MAX));

    }

    public int bulbSwitch(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    count++;
                }
            }
            if (count % 2 ==1) {
                sum++;
            }
        }
        return sum;
    }

    public int bulbSwitchOpt(int n) {
        int sum = 0;
        for (int i = 1; i <= (n > 4 ? n / 2: n); i++) {
            if (Math.pow(i, 2) > n) {
                break;
            }
            sum++;
        }
        return sum;
    }

    public int bulbSwitchOpt1(int n) {
        if (n < 0) return 0;
        return (int) Math.sqrt(n);
    }
}
