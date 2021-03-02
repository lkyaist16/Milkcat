import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList();
        TreeNode cur = root;
        TreeNode pre = null;

        while (cur != null || !queue.isEmpty()) {
            //左节点压进栈
            while (cur != null) {
                queue.push(cur);
                cur = cur.left;
            }
            //左节点出栈
            cur = queue.pop();
            //如果当前节点的右节点为空
            if (cur.right == null || cur.right == pre) {
                res.add(cur.val);
                pre = cur;
                cur = null;
            }
            //否则右节点进栈
            else {
                queue.push(cur);
                cur = cur.right;
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
