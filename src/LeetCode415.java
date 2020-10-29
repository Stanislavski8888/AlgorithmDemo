/**
 * <a href="https://leetcode-cn.com/problems/add-strings/">415. 字符串相加</a><br>
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <br>
 * <pre>
 * 提示：
 *
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 * </pre>
 */
public class LeetCode415 {
    static final long NUM1 = (long)(Math.random() * Long.MAX_VALUE);
    static final long NUM2 = (long)(Math.random() * Long.MAX_VALUE);

    public static void main(String[] args) {
        System.out.println("NUM1 = " + String.format("%19d", NUM1));
        System.out.println("NUM2 = " + String.format("%19d", NUM2));
        String number1 = String.valueOf(NUM1);
        String number2 = String.valueOf(NUM2);

        LeetCode415 instance = new LeetCode415();
        String result = instance.addStrings(number1, number2);

        System.out.println("result = " + result);
    }

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int length1 = num1.length();
        int length2 = num2.length();
        int length = Math.max(length1, length2);
        boolean carry = false;
        String head = null;
        for (int i = 0; i < length; i++) {
            int index1 = length1 - 1 - i;
            int index2 = length2 - 1 - i;
            if (index1 < 0) {
                if (carry) {
                    int n = num2.charAt(index2) - 48;
                    n += 1;
                    carry = n >= 10;
                    sb.append(carry ? n - 10 : n);
                } else {
                    head = num2.substring(0, index2 + 1);
                    break;
                }
            } else if (index2 < 0) {
                if (carry) {
                    int n = num1.charAt(index1) - 48;
                    n += 1;
                    carry = n >= 10;
                    sb.append(carry ? n - 10 : n);
                } else {
                    head = num1.substring(0, index1 + 1);
                    break;
                }
            } else {
                char n1 = num1.charAt(index1);
                char n2 = num2.charAt(index2);
                int n = n1 + n2 - 96;
                n = carry ? n + 1 : n;
                carry = n >= 10;
                sb.append(carry ? n - 10 : n);
            }
        }
        if (carry) {
            sb.append(1);
        }
        sb.reverse();
        if (head != null) {
            sb.insert(0, head);
        }
        return sb.toString();
    }

    //以下是官方解法
    public String addStringsOfficial(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }

}
