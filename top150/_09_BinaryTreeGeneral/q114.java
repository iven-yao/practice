package top150._09_BinaryTreeGeneral;

import node_definition.TreeNode;

public class q114 {
    public void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode right = root.right;
        TreeNode left = root.left;
        root.left = null;
        flatten(left);
        flatten(right);
        root.right = left;
        TreeNode ptr = root;
        while(ptr.right != null) ptr = ptr.right;
        ptr.right = right;
    }
}
