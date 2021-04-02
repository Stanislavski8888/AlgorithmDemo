import utils.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 请用任意语言实现从一个整形数组中统计重复次数最多的元素，输出该元素，对并列重复次数最多的输出任意一个。<br>
 * 分析您写的代码的时空复杂度。
 * <br>
 * <pre>
 * 举例1
 * 输入：1 1 1 2 3 4 5
 * 输出：1
 * </pre>
 * <pre>
 * 举例2
 * 输入：1 1 2 2 3 3 3 3 3 3 3 3 3 3 3 4 4 5 5 6 6 7
 * 输出：3
 * </pre>
 */
public class Question2 {
    public static void main(String[] args) {
        Question2 instance = new Question2();
        int[] array = Utils.genarateRandomArray(5, 10);

        System.out.println(instance.findRepeatNumber(array));
    }

    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                int times = hashMap.get(nums[i]);
                hashMap.put(nums[i], ++times);
            } else {
                hashMap.put(nums[i], 0);
            }
        }

        int result = -1;
        int maxTimes = Integer.MIN_VALUE;
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iterator.next();
            if (entry.getValue() > maxTimes) {
                maxTimes = entry.getValue();
                result = entry.getKey();
            }
        }

        return result;
    }
}