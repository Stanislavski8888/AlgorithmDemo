import java.util.HashMap;

/**
 * 问题描述：给定一个字符串，找出这个字符串中最长的不重复子串。比如对于字符串“sadus”，那么返回的结果应该是“sadu”或者“adus”（返回一个即可）；
 * 对于字符串“acbba”，返回的应是“acb”。
 * <br>
 * 具体的思路是这样的：遍历整个字符串，针对每个字符做三件事
 * <br>
 * <ol>
 *      <li>如果当前字符出现过并且该字符上一次出现时的index大于目前子串的初始index，那么将目前子串的初始index置为该字符上一次出现时的index。
 *      注意这个子串不一定是最大长度的子串，而是程序运行过程中当前的不重复子串。</li>
 *      <li>如果当前的index与startIndex（目前子串的初始index）的差值大于maxLen（记录的最长不重复子串的长度），那么更新maxLen。
 *      注意，因为我们要输出这个不重复子串，所以我们在这时候（maxLen改变时）需要记录startIndex。</li>
 *      <li>记录当前字符的index。</li>
 * </ol>
 * 这样扫描完以后，我们就可以根据oriStartIndex（maxLen改变时记录的startIndex）和maxLen来得到最长不重复子串。
 *
 * @author root
 *
 */
public class MaxSubString {
    public static void main(String[] args) {

        String str = "jhhjnsfudufbdfyscfbsdjjSAFASFsefyrsjksaudhsduhasdbg"
                + "ywqep[m,mzaASFASFhuwenzsdjsjfasfyaehwzsjx ;ASFASFlpwisjd"
                + "asuwnad.pqwekASFqowe92347174nsd sdfsdf73bwASFawue821b9sa"
                + "dasjdnasdqASFASASFF2en128adasjdnqwudSAASFF	ASFw 1wq89ewa"
                + "dasjdASFhqw8edeqwhedhqwueASFuquweuqwuehqw e123xrkASFajs8" + "9da2qe54we24eDASASAFFdfsdifhsd";
        long a = System.nanoTime();
        for (int count = 0; count < 100000; count++) {
            findLongestSubString1(str);
        }
        long b = System.nanoTime() - a;
        System.out.println(b);

        a = System.nanoTime();
        for (int count = 0; count < 100000; count++) {
            findLongestSubString2(str);
        }
        b = System.nanoTime() - a;
        System.out.println(b);

        System.out.println(findLongestSubString1(str));
        System.out.println(findLongestSubString2(str));
    }

    private static String findLongestSubString1(String str) {
        if (str == null)
            return null;
        StringBuilder maxSubString = new StringBuilder("");
        char[] strCharArr = str.toCharArray();
        HashMap<Character, Integer> charsIndex = new HashMap<Character, Integer>();
        int startIndex = -1, oriStartIndex = startIndex, maxLen = 0;
        for (int index = 0; index < strCharArr.length; index++) {
            if (charsIndex.containsKey(strCharArr[index])) {
                int oriIndex = charsIndex.get(strCharArr[index]);
                if (oriIndex > startIndex) {
                    startIndex = oriIndex;
                }
            }
            if (index - startIndex > maxLen) {
                maxLen = index - startIndex;
                oriStartIndex = startIndex;
            }
            charsIndex.put(strCharArr[index], index);
        }
        for (int index = oriStartIndex + 1; index < oriStartIndex + maxLen + 1; index++) {
            maxSubString.append(strCharArr[index]);
        }
        return maxSubString.toString();
    }

    private static String findLongestSubString2(String str) {
        if (str == null)
            return null;
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
        return maxSubString.toString();
    }

}