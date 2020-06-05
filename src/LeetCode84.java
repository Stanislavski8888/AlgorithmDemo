import java.util.Arrays;
import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。<br>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。 <br>
 * 其中每个柱子的宽度为 1，如给定的高度为 [2,1,5,6,2,3]。
 * <br>
 * 阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * <br>
 * 示例:
 * <ul>
 *  <li>输入: [2,1,5,6,2,3]</li>
 *  <li>输出: 10</li>
 * </ul>
 * <a href="https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode-/">柱状图中最大的矩形</a>
 */
public class LeetCode84 {
    private static final int MAX = 20;
    public static void main(String[] args) {
        LeetCode84 leetCode84 = new LeetCode84();
        int length = (int) (Math.random() * 10 + 3);
        int[] numbers = Utils.genarateRandomArray(MAX, length);

        System.out.println("Max ans = " + leetCode84.largestRectangleArea(numbers));
    }

    int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.empty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = (stack.empty() ? -1 : stack.peek());
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stack.empty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = (stack.empty() ? n : stack.peek());
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    int largestRectangleAreaOpt(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Stack<Integer> mono_stack = new Stack<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
