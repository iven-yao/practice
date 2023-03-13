package leetcode;
import node_definition.TreeNode;

public class q101 {
    public boolean isSymmetric(TreeNode root) {
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode leftTree, TreeNode rightTree) {
        if(leftTree == null && rightTree == null) return true;
        if(leftTree == null || rightTree == null) return false;

        return helper(leftTree.left, rightTree.right) && leftTree.val == rightTree.val && helper(leftTree.right, rightTree.left);
    }
}
