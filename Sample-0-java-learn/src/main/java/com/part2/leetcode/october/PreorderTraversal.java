package com.part2.leetcode.october;

import com.part2.leetcode.mianshi.KthLargest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 * <p>
 *  示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PreorderTraversal {
    public List<Integer> res = new ArrayList<>();
//    public List<Integer> preorderTraversal(TreeNode root) {
//        dfs(root);
//
//
//        return res;
//    }



    /**
     *     方法一：递归实现dfs
     *     复杂度分析：
     *
     *     时间复杂度：O(n)，其中 n 是二叉树的节点数。每一个节点恰好被遍历一次。
     *
     *     空间复杂度：O(n)，为递归过程中栈的开销，平均情况下为O(logn)，最坏情况下树呈现链状，为 O(n)。
     */
//    public void dfs(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        res.add(root.val);
//        dfs(root.left);
//        dfs(root.right);
//    }

    //方法二：迭代实现，递归隐式维护了一个栈，迭代显式实现出来
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) {
           return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();

        TreeNode node = root;

        while (!stack.isEmpty() || node!=null) {
            while (node !=null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {

        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
