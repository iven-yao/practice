package top150._09_BinaryTreeGeneral;

import node_definition.TreeNode;

public class q129 {
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        helper(root, 0);

        return sum;
    }

    private void helper(TreeNode node, int curr) {
        if(node == null) return;

        curr *= 10;
        curr += node.val;

        if(node.left == null && node.right == null) sum += curr;
        helper(node.left, curr);
        helper(node.right, curr);

    }
}
