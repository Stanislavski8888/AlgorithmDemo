public class Knapsack {
    private static final int LENGTH = 6;
    private static final int CAPACITY = 20;
    int[][] memo = new int[LENGTH][];


    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        int[] weights = new int[LENGTH];
        int[] values = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            weights[i] = (int)(Math.random() * 10);
//            System.out.printf("weight[" + i + "] = " + weights[i]);
        }
//        System.out.println();
        for (int i = 0; i < LENGTH; i++) {
            values[i] = (int) (Math.random() * 10 + 1);
//            System.out.println("value[" + i + "] = " + values[i]);
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
            knapsack.memo[i] = new int[CAPACITY + 1];
        }
        int best = knapsack.bestValue(weights, values, LENGTH - 1, CAPACITY);
        System.out.println("best = " + best);
    }

    int bestValue(int[] weight, int[] values, int index, int cap) {
        if (cap <= 0 || index < 0) {
            return 0;
        }

        if (memo[index][cap] != -1) {
            return memo[index][cap];
        }

        int res = bestValue(weight, values, index - 1, cap);
        if (cap >= weight[index]) {
            res = Math.max(res, values[index] + bestValue(weight, values, index - 1, cap - weight[index]));
        }

        memo[index][cap] = res;
        return res;
    }
}
