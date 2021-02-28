package com.part2.leetcode.year2021.math;

import java.util.*;

public class Main {
    public static int findShortestSubArray(int[] nums) {
        //算度,用数组记录出现次数、第一次出现的位置和最后一次出现的位置
        Map<Integer, int[]> map = new HashMap();
        int maxValue = 0;
        for (int i = 0; i < nums.length; i++) {
            //如果有这个值
            if (map.containsKey(nums[i])) {
                int[] record = map.get(nums[i]);
                record[0]++;
                record[2] = i;
                maxValue = Math.max(maxValue, record[0]);
                map.put(nums[i], record);
            } else {
                //如果没有这个值
                //初始化数组
                int[] record = new int[3];
                //次数
                record[0] = 0;
                //第一次出现位置
                record[1] = i;
                //最后一次出现位置
                record[2] = i;
                map.put(nums[i], record);
            }
        }

        //算最小连续子数组
        int result = nums.length;
        Iterator<Map.Entry<Integer, int[]>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            //获取记录的数组
            Map.Entry<Integer, int[]> next = iterator.next();
            int[] record = next.getValue();
            if (record[0] == maxValue) {
                //计算长度
                int length = record[2] - record[1] + 1;
                result = Math.min(length, result);
            }
        }
        return result;
    }

    public String mergeAlternately(String word1, String word2) {
        String result = "";
        Queue<String> word1List = new LinkedList();
        Queue<String> word2List = new LinkedList();

        //队列保存word
        for (int i = 0; i < word1.length(); i++) {
            word1List.offer(word1.substring(i, i + 1));
        }

        for (int i = 0; i < word2.length(); i++) {
            word2List.offer(word2.substring(i, i + 1));
        }

        for (int i = 0; i < word1.length() + word2.length(); i++) {
            if (word1List.isEmpty()) {
                result += word2List.poll();
                continue;
            }

            if (word2List.isEmpty()) {
                result += word1List.poll();
                continue;
            }

            //偶数放word1
            if (i % 2 == 0) {
                result += word1List.poll();
            }
            //基数放word2
            else {
                result += word2List.poll();
            }
        }
        return result;
    }

    public int[] minOperations(String boxes) {
        int[] answer = new int[boxes.length()];
        for (int i = 0; i < boxes.length(); i++) {
            int moves = 0;
            //左遍历
            for (int j = i; j >= 0; j--) {
                if ("1".equals(String.valueOf(boxes.charAt(j)))) {
                    answer[i] += i - j;
                }
            }
            //右遍历
            for (int j = i; j < boxes.length(); j++) {
                if ("1".equals(String.valueOf(boxes.charAt(j)))) {
                    answer[i] += j - i;
                }
            }
        }
        return answer;
    }


    public boolean isToeplitzMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            if (i - 1 < 0) {
                continue;
            }
            for (int j = 0; j < matrix[i].length; j++) {
                if (j - 1 < 0) {
                    continue;
                }
                if (matrix[i - 1][j - 1] != matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int left = 0;
        int leftSum = 0;
        int right = 0;
        int rightSum = 0;
        int winSum = 0;
        int winSize = 0;
        int n = customers.length;

        //初始化右窗口的值
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                rightSum += customers[i];
            }
        }
        int res = rightSum;

        while (right < n) {
            //算窗口的值
            winSum += customers[right];
            if (grumpy[right] == 0) {
                //算右窗口的值
                rightSum -= customers[right];
            }
            winSize++;
            while (winSize > X) {
                winSum -= customers[left];
                if (grumpy[left] == 0) {
                    //算左窗口的值
                    leftSum += customers[left];
                }
                left++;
                winSize--;
            }
            if (winSize == X) {
                res = Math.max(res, leftSum + winSum + rightSum);
            }
            right++;
        }
        return res;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //比较大小，小于就交换
        //初始化头节点
        ListNode root = new ListNode(0);
        //指针节点
        ListNode current = root;
        //当两个都不为空
        while (l1 !=null && l2!=null) {
            if(l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = l1 != null ? l1 : l2;
        return root.next;
    }

    public int[][] flipAndInvertImage(int[][] A) {
        for (int[] a : A) {
            int left = 0;
            int right = a.length - 1;
            while (right > left) {
                int tem = a[left] ^ 1;
                a[left++] = a[right] ^ 1;
                a[right--] = tem;
            }
        }
        return A;
    }

    public int longestSubstring(String s, int k) {
        if(s.length()< k) {
            return 0;
        }
        //分治法 分段计算 迭代
        //如何考虑用哪种算法？1。理解题意：求xx最长子串，一般为分治法，滑动窗口法 2。要统计每个字符出现次数，可以用hashmap
        //计算每个字符出现的次数
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 1));
        }
        //循环遍历每个字符
        for (char ch : map.keySet()) {
            //如果字符出现次数小于k，进行分段
            if(map.get(ch) < k) {
                int res = 0;
                String[] split = s.split(String.valueOf(ch));
                //对分段里的字符再进行循环判断
                for (String x: split) {
                    res = Math.max(res, longestSubstring(x, k));
                }
            }

        }
        //如果字符串里每个字符都大于k直接返回
        return s.length();
    }

    public static int[] reversePrint(ListNode head) {
        Deque<Integer> queue = new LinkedList();
        ListNode cur = head;
        while(cur!=null) {
            queue.push(cur.val);
            cur = cur.next;
        }
        int n = queue.size();
        int[] res = new int[n];
        for(int i=0;i<n;i++){
            res[i] = queue.pop();
        }
        return res;
    }




    public static void main(String[] args) {
        int[] a = new int[]{1,1,0};
    }
}
