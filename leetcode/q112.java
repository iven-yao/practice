package leetcode;

import node_definition.TreeNode;

public class q112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return checkSum(root, 0, targetSum);
    }

    public boolean checkSum(TreeNode node, int val, int target) {
        if(node == null) return false;
        if(node.left == null && node.right == null) return (val+node.val) == target;
        val = val + node.val;
        return checkSum(node.left, val, target) || checkSum(node.right, val, target);
    }
}
