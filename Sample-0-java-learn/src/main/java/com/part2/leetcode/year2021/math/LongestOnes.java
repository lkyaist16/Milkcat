package com.part2.leetcode.year2021.math;

/**
 * 1004. 最大连续1的个数 III
 */
public class LongestOnes {
    //滑动窗口
    public int longestOnes(int[] A, int K) {
        //数组长度
        int n = A.length;
        //窗口右指针
        int right = 0;
        //窗口左指针
        int left = 0;
        //包含0的数量
        int zeros = 0;
        //结果窗口长度
        int res = 0;

        //右移
        while (right < n) {
            //如果右移遇到0了，记录需要补的0
            if(A[right] == 0) {
                zeros ++;
            }
            //如果需要补的0超过K，需要移动left
            while (zeros > K) {
                if(A[left++] == 0) {
                    zeros --;
                }
            }
            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }

}
