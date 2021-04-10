import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <br>
 * 注意：答案中不可以包含重复的三元组。
 *
 * <pre>
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * </pre>
 * <pre>
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * </pre>
 * <pre>
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 * </pre> 
 * <pre>
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * </pre>
 */
public class LeetCode15 {
    public static void main(String[] args) {
        Utils.genaratePositiveNegative(5, 20);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }
}
