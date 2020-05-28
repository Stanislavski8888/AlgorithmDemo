/**
 * 对于F(i,c)，有两种情况，将第i个物品加入和直接忽略第i个物品
 * <br>
 * <b>F(i,C) = max{F(i-1, C), v(i) + F(i-1, C-w(i))}</b>
 **/
public class Knapsack {
    private static final int LENGTH = 6;
    private static final int CAPACITY = 20;

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        int[][] items = new int[LENGTH][];
        int[] weights = new int[LENGTH];
        int[] values = new int[LENGTH];

        //随机生成weights和values，随后打印
        for (int i = 0; i < LENGTH; i++) {
            weights[i] = (int)(Math.random() * 10 + 1);
        }
        for (int i = 0; i < LENGTH; i++) {
            values[i] = (int) (Math.random() * 10 + 1);
        }
        StringBuilder wsb = new StringBuilder("weights = [");
        for (int weight : weights) {
            wsb.append(weight + ", ");
        }
        wsb.replace(wsb.length() - 2, wsb.length(), "");
        wsb.append("]");
        System.out.println(wsb.toString());

        StringBuilder vsb = new StringBuilder("values = [");
        for (int value : values) {
            vsb.append(value + ", ");
        }
        vsb.replace(vsb.length() - 2, vsb.length(), "");
        vsb.append("]");
        System.out.println(vsb.toString());


        for (int i = 0; i < LENGTH; i++) {
            items[i] = new int[] {weights[i], values[i]};
        }

        int best = knapsack.bestValueOpt2(items, CAPACITY);
        System.out.println("best = " + best);
    }

    //方法一
    int bestValue(int[][] items, int cap) {
        if (cap <= 0 || items.length <= 0) {
            return 0;
        }
        int N = items.length;
        int[][] memo = new int[N][cap + 1];
        for (int i = 0; i <= cap; i++) {
            memo[0][i] = i >= items[0][0] ? items[0][1] : 0;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= cap; j++) {
                // 0~i这些物品容积为j的背包获得的最大值
                memo[i][j] = memo[i - 1][j];
                if (j >= items[i][0]) {
                    memo[i][j] = Math.max(memo[i][j], memo[i - 1][j - items[i][0]] + items[i][1]);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                System.out.print("memo[" + i + ", " + j + "] = " + memo[i][j]);
            }
        }

        return memo[N - 1][cap];
    }

    //优化一：两行轮流使用
    int bestValueOpt1(int[][] items, int cap) {
        if (cap <= 0 || items.length <= 0) {
            return 0;
        }
        int N = items.length;
        int[][] memo = new int[2][cap + 1];
        for (int i = 0; i <= cap; i++) {
            memo[0][i] = i >= items[0][0] ? items[0][1] : 0;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= cap; j++) {
                memo[i % 2][j] = memo[(i - 1) % 2][j];
                if (j >= items[i][0]) {
                    memo[i % 2][j] = Math.max(memo[i % 2][j], memo[(i - 1) % 2][j - items[i][0]] + items[i][1]);
                }
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                System.out.println("memo[" + i + ", " + j + "] = " + memo[i][j]);
            }
        }

        return memo[(N - 1) % 2][cap];
    }

    //优化二；只使用一行，从右向左刷新内容
    int bestValueOpt2(int[][] items, int cap) {
        if (cap <= 0 || items.length <= 0) {
            return 0;
        }
        int N = items.length;
        int[] memo = new int[cap + 1];
        for (int i = 0; i <= cap; i++) {
            memo[i] = i >= items[0][0] ? items[0][1] : 0;
        }

        for (int i = 1; i < N; i++) {
            for (int j = cap; j >= items[i][0]; j--) {
                memo[j] = Math.max(memo[j], memo[j - items[i][0]] + items[i][1]);
            }
        }

        StringBuilder sb = new StringBuilder("memo[");
        for (int j = 0; j < memo.length; j++) {
            sb.append(j == (memo.length - 1 ) ? memo[j] + "]" : memo[j] + ", ");
        }
        System.out.println(sb.toString());


        return memo[cap];
    }
}
