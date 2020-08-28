package com.part2.leetcode.august;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 *
 * 示例:
 *
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 *
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindSubSequences {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null) {
            return null;
        }
        dfs(0, Integer.MIN_VALUE, nums);
        return res;
    }

    public void dfs(int curIndex, int preValue, int[] nums) {

        if(curIndex >= nums.length) {
            if(temp.size() >= 2) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }

        if(nums[curIndex] >= preValue) {
            temp.add(nums[curIndex]);
            dfs(curIndex+1, nums[curIndex], nums);
            temp.remove(temp.size() -1);
        }

        if(nums[curIndex] != preValue) {
            dfs(curIndex + 1, preValue, nums);
        }
    }



}
