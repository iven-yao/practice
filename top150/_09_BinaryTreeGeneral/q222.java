package top150._09_BinaryTreeGeneral;

import node_definition.TreeNode;

public class q222 {
    int sum = 0;
    
    public int countNodes(TreeNode root) {
        dfs(root);
        return sum;    
    }

    public void dfs(TreeNode node) {
        if(node == null) return;

        dfs(node.left);
        sum++;
        dfs(node.right);
    }
}
