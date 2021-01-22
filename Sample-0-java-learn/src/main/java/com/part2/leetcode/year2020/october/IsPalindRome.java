package com.part2.leetcode.year2020.october;


import java.util.ArrayList;
import java.util.List;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsPalindRome {
    public boolean isPalindrome(ListNode head) {
        List<ListNode> listNodes = new ArrayList<>();

        while (head != null) {
            listNodes.add(head);
            head = head.next;
        }

        int i = 0;
        int j = listNodes.size() - 1;

        while (i < j) {
            if (listNodes.get(i).val == listNodes.get(j).val) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
