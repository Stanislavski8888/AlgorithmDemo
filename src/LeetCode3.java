import utils.Utils;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/">3. 无重复字符的最长子串</a><br>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。<br>
 *
 * <pre>
 * <strong>示例 1:</strong>
 * <strong>输入:</strong> "abcabcbb"
 * <strong>输出:</strong> 3
 * <strong>解释:</strong> 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * </pre>
 *
 * <pre>
 * <strong>示例 2:</strong>
 * <strong>输入:</strong> "bbbbb"
 * <strong>输出:</strong> 1
 * <strong>解释:</strong> 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * </pre>
 *
 * <pre>
 * <strong>示例 3:</strong>
 * <strong>输入:</strong> "pwwkew"
 * <strong>输出:</strong> 3
 * <strong>解释:</strong> 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是<strong>子串</strong>的长度，"pwke" 是一个<strong>子序列</strong>，不是子串。
 * </pre>
 */
public class LeetCode3 {
    public static void main(String[] args) {
        String string = Utils.generateEasyRepeatString(20);
        System.out.println("string = " + string);
        LeetCode3 instance = new LeetCode3();
        int longest = instance.lengthOfLongestSubstring(string);
        System.out.println("longest = " + longest);
        int longestOf = instance.lengthOfLongestSubstringOfficial(string);
        System.out.println("longestOf = " + longestOf);
    }

    /**
     * 我的方法
     */
    public int lengthOfLongestSubstring(String str) {
        if (str == null)
            return 0;
        StringBuilder maxSubString = new StringBuilder("");
        char[] strCharArr = str.toCharArray();
        int[] charsIndex = new int[256];
        for (int index = 0; index < 256; index++)
            charsIndex[index] = -1;
        int startIndex = -1, oriStartIndex = startIndex, maxLen = 0;
        for (int index = 0; index < strCharArr.length; index++) {
            if (charsIndex[strCharArr[index]] > startIndex)
                startIndex = charsIndex[strCharArr[index]];
            if (index - startIndex > maxLen) {
                maxLen = index - startIndex;
                oriStartIndex = startIndex;
            }
            charsIndex[strCharArr[index]] = index;
        }
        for (int index = oriStartIndex + 1; index < oriStartIndex + maxLen + 1; index++) {
            maxSubString.append(strCharArr[index]);
        }
        return maxSubString.toString().length();
    }

    /**
     * 官方方法
     */
    public int lengthOfLongestSubstringOfficial(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}