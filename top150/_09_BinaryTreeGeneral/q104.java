package top150._09_BinaryTreeGeneral;

import node_definition.TreeNode;

public class q104 {
    public int maxDepth(TreeNode root) {
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        if(node == null) return 0;
        
        return Math.max(dfs(node.left), dfs(node.right))+1;
    }
}
