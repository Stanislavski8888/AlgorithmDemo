/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。<br>
 * <pre>
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。<br>
 * </pre>
 * <pre>
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 * </pre>
 * <pre>
 * 示例 3:
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 * </pre>
 *
 * <a href="https://leetcode-cn.com/problems/repeated-substring-pattern">459. 重复的子字符串</a>
 */
public class LeetCode459 {
    public static void main(String[] args) {
        LeetCode459 leetCode = new LeetCode459();
//        String str = utils.Utils.generateRandomString(100);
        String str = "abcdeabcdeabcde";
        System.out.println("str = " + str);
        System.out.println(leetCode.repeatedSubstringPattern(str));
    }
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}