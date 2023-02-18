package leetcode;

import node_definition.TreeNode;

public class q226 {
    public TreeNode invertTree(TreeNode root) {
        invertTreeHelper(root);

        return root;
    }

    public void invertTreeHelper(TreeNode node) {
        if(node == null) return;
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        invertTreeHelper(node.left);
        invertTreeHelper(node.right);
    }
}
