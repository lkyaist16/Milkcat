package com.part2.leetcode.year2020.march;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 * <p>
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= target <= 10^5
 */
public class FindContinumousSequence {

    public static int[][] findContinuousSequence(int target) {

        List<int[]> resultList = new ArrayList();
        for (int i = 1; i < target - 1; i++) {
            int sum = 0;
            int j = i;
            List<Integer> list = new ArrayList();
            while (sum < target) {
                sum += j;
                list.add(j);
                j++;
            }
            if (sum == target) {
                int[] result = new int[list.size()];
                for (int k = 0; k < list.size(); k++) {
                    result[k] = list.get(k);
                }
                resultList.add(result);
            }
        }
        int[][] sequence = new int[resultList.size()][];
        for (int i = 0; i < resultList.size(); i++) {
            sequence[i] = resultList.get(i);
        }

        return sequence;
    }

    public static void main(String[] args) {
        int[][] sequence = findContinuousSequence(9);
        for (int i = 0; i < sequence.length; i++) {
            int[] a = sequence[i];
            for(int j = 0; j< sequence[i].length; j++) {
                System.out.println(a[j]);
            }

        }
    }
}
