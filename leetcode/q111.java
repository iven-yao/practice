package leetcode;
import node_definition.TreeNode;

public class q111 {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        if(root.left == null) return minDepth(root.right)+1;
        if(root.right == null) return minDepth(root.left)+1;
        return Math.min(minDepth(root.left), minDepth(root.right)) +1;
    }
}
