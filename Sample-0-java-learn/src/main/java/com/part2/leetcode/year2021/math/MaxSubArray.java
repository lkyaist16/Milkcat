package com.part2.leetcode.year2021.math;

/**
 * 53. 最大子序和
 *
 */
public class MaxSubArray {
    //解法一：动态规划
    //第i个数包含or不包含的问题
    public int maxSubArray(int[] nums) {
       int pre = 0;
       int maxValue = 0;
       for (int x : nums) {
           pre = Math.max(pre + x, x);
           maxValue = Math.max(pre, maxValue);
       }
       return maxValue;
    }
}
