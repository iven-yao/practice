package top150._09_BinaryTreeGeneral;

import node_definition.TreeNode;

public class q112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return helper(root, 0, targetSum);
    }

    private boolean helper(TreeNode node, int sum, int targetSum) {
        if(node == null) return false;
        sum += node.val;
        if(node.left == null && node.right == null) return sum == targetSum;
        boolean left =  helper(node.left, sum, targetSum); 
        boolean right = helper(node.right, sum, targetSum);
        
        return left || right;
    }
}
