package com.part2.leetcode.mianshi.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 * <p>
 * 例如：
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * 节点总数 <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxDepth {
//    public int maxDepth(TreeNode root) {
//        //递归实现的深度优先搜索法
//        if(root == null) return 0;
//        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
//    }


    /**
     * 层序遍历
     * BFS 广度优先搜索
     * 一层一层的开始遍历，从上到下
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {

        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();

        int res = 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            res++;
            int n = queue.size();
            //记录下一层的节点
            for (int i = 0; i < n; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
