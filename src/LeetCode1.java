import utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/two-sum/">两数之和</a><br>
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。<br>
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。<br>
 *
 * 你可以按任意顺序返回答案。<br>
 *
 */
public class LeetCode1 {
    private static final int MAX = 20;

    public static void main(String[] args) {
        int length = (int) Math.random() * 10 + 10;
        int target = (int) (Math.random() * 20) + 20;
        int[] nums = Utils.genarateRandomArray(MAX, length);
        System.out.println("target = " + target);

        LeetCode1 instance = new LeetCode1();
//        int[] result = instance.twoSum(nums, target);
        int[] result = instance.twoSumOfficial(nums, target);
        for (int a : result) {
            System.out.print(a + " ");
        }
    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[] { -1, -1 };
        if (nums.length <= 2) {
            return null;
        }
        for (int i = 0; i < nums.length; i++) {
            int k = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (k == nums[j]) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。因此，我们需要一种更优秀的方法，能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。<br>
     *
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N)O(N) 降低到 O(1)O(1)。<br>
     *
     * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。<br>
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumOfficial(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

}