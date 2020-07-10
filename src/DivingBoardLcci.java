import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 * <br>
 * 返回的长度需要从小到大排列。
 * <br>
 * 示例：
 * <br>
 * <pre>
 * 输入：
 *    shorter = 1
 *    longer = 2
 *    k = 3
 * </pre>
 * 输出： {3,4,5,6}
 * <br>
 * 提示：
 * <ul>
 *     <li>0 < shorter <= longer</li>
 *     <li>0 <= k <= 100000</li>
 * </ul>
 * <a href="https://leetcode-cn.com/problems/diving-board-lcci">跳水板</a>
 */
public class DivingBoardLcci {
    public static void main(String[] args) {
        DivingBoardLcci divingBoardLcci = new DivingBoardLcci();
        int shorter = (int) (Math.random() * 10);
        int longer = (int) (Math.random() * 10);
        while (longer < shorter) {
            longer = (int) (Math.random() * 10);
        }
        int k = (int) (Math.random() * 10);
        System.out.println("shorter = " + shorter + ", longer = " + longer + ", k = " + k);
        int[] res = divingBoardLcci.divingBoard(shorter, longer, k);
        if (res != null) {
            for (int r : res) {
                System.out.print(r + " ");
            }
        } else if (res == null || res.length == 0) {
            System.out.println("Invalid result!");
        }
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        if (shorter > longer || k <= 0 || k > 100000) {
            return null;
        }
        if (shorter == longer) {
            return new int[]{ shorter * k };
        }
        int[] result = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            result[i] = shorter * i + longer * (k - i);
        }
        Arrays.sort(result);

        return result;
    }
}
