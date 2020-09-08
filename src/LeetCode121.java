/**
 * <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock">121. 买卖股票的最佳时机</a>
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * <strong>注意：</strong>你不能在买入股票前卖出股票。
 * <p>
 *
 * <pre>
 * <strong>示例 1:</strong>
 *
 * <strong>输入:</strong> [7,1,5,3,6,4]
 * <strong>输出:</strong> 5
 * <strong>解释:</strong> 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * </pre>
 * <pre>
 * <strong>示例 2:</strong>
 *
 * <strong>输入:</strong> [7,6,4,3,1]
 * <strong>输出:</strong> 0
 * <strong>解释:</strong> 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * </pre>
 */
public class LeetCode121 {
    static final int DAYS = (int) (Math.random() * 10 + 1);
    static final int MAX = 20;

    public static void main(String[] args) {
        int[] prices = Utils.genarateRandomArray(MAX, DAYS);
        LeetCode121 instance = new LeetCode121();
        int max = instance.maxProfit(prices);
        System.out.println("max = " + max);
    }

    /**
     * 时间复杂度: O(N^2)
     */
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int max = 0;
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                max = Math.max(max, prices[i] - prices[j]);
            }
        }
        if (max < 0) {
            max = 0;
        }
        return max;
    }

    /**
     * 一次遍历
     */
    public int maxProfitOpt(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    /**
     * 动态规划
     */
    private int maxProfitDP(int[] prices) {

        return 0;
    }
}
