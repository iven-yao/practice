package top150._09_BinaryTreeGeneral;

import node_definition.TreeNode;

public class q226 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;

        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);

        return root;
    }
}
