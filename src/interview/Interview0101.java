package interview;

import utils.Utils;

/**
 * <a href="https://leetcode-cn.com/problems/is-unique-lcci/">面试题 01.01. 判定字符是否唯一</a><br>
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。<br>
 * <p>
 * 示例 1：
 * <pre>
 * 输入: s = "leetcode"
 * 输出: false
 * </pre>
 * 示例 2：
 * <pre>
 * 输入: s = "abc"
 * 输出: true
 * </pre>
 * 限制：
 * <pre>
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 * </pre>
 */
public class Interview0101 {
    public static void main(String[] args) {
        int length = (int) (Math.random() * 20) + 10;
        Interview0101 instance = new Interview0101();
        String str = Utils.generateVisibleString(length);

    }

    public boolean isUnique(String astr) {
        return false;
    }
}