package meta;

import node_definition.TreeNode;

public class f32_l1123 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        int maxDepth = 0;
        TreeNode res;
        public TreeNode lcaDeepestLeaves(TreeNode root) {
            traverse(root, 1);
            return res;
        }

        private int traverse(TreeNode node, int depth) {
            if(node == null) return depth-1;
            maxDepth = Math.max(maxDepth, depth);
            int left = depth;
            int right = depth;
            if(node.left != null) left = traverse(node.left, depth+1);
            if(node.right!= null)right = traverse(node.right, depth+1);

            if(left == maxDepth && right == maxDepth) {
                res = node;
            }

            return Math.max(left, right);
        }   
    }
}
