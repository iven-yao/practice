package meta;

import node_definition.TreeNode;

public class f17_l236 {
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(root == null|| root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if(left == null) return right;
            if(right == null) return left;
            if(left != null & right != null) return root;
            return null;
        }
    }
}
