package top150._09_BinaryTreeGeneral;

import java.util.Stack;

import node_definition.TreeNode;

public class q173 {
    class BSTIterator {

        Stack<TreeNode> left;

        public BSTIterator(TreeNode root) {
            left = new Stack<>();
            dfs(root);
        }

        private void dfs(TreeNode node) {
            while(node != null) {
                left.push(node);
                node = node.left;
            }
        }
        
        public int next() {
            TreeNode node = left.pop();
            dfs(node.right);

            return node.val;  
        }
        
        public boolean hasNext() {
            return !left.isEmpty();
        }
    }
}
