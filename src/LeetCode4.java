import utils.Utils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/median-of-two-sorted-arrays/">寻找两个正序数组的中位数</a><br>
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。<br>
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。<br>
 *
 */
public class LeetCode4 {
    private static final int MAX = 20;

    public static void main(String[] args) {
        int[] nums1 = Utils.genarateRandomArray(MAX, (int)(Math.random() * 10) + 10);
        int[] nums2 = Utils.genarateRandomArray(MAX, (int)(Math.random() * 10) + 10);
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Utils.printIntegerArray(nums1);
        Utils.printIntegerArray(nums2);
        LeetCode4 instance = new LeetCode4();
        double result = instance.findMedianSortedArrays(nums1, nums2);
        System.out.println("result = " + result);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mergeLength = nums1.length + nums2.length;
        int[] merge = new int[mergeLength];

        for(int i = 0; i < mergeLength; i++) {
            merge[i] = i < nums1.length ? nums1[i] : nums2[i - nums1.length];
        }
        Arrays.sort(merge);
        Utils.printIntegerArray(merge);

        return mergeLength % 2 == 0
                ? ((double) (merge[mergeLength /2 ] + merge[mergeLength / 2 - 1])) / 2
                : merge[mergeLength / 2];
    }
}