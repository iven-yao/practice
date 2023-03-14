package leetcode;

import node_definition.TreeNode;

public class q129 {
    public int sumNumbers(TreeNode root) {
        return sumHelper(root, 0);
    }

    public int sumHelper(TreeNode node, int val) {
        if(node == null) return 0;
        if(node.left == null && node.right == null) return val*10 + node.val;

        val = val*10 + node.val;
        return sumHelper(node.left, val) + sumHelper(node.right, val);
    }
}
