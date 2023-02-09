package leetcode;
import java.util.Arrays;

import node_definition.TreeNode;

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
class q606 {
    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        tree2strHelper(root, sb);
        return sb.toString();
    }
    
    public void tree2strHelper(TreeNode root, StringBuilder sb) {
        if(root == null) return;
        
        sb.append(root.val);
        if(root.left!=null) {
            sb.append("(");
            tree2strHelper(root.left,sb);
            sb.append(")");
        } else {
            if(root.right!=null) {
                sb.append("()");
            }
        }
        if(root.right!=null) {
            sb.append("(");
            tree2strHelper(root.right,sb);
            sb.append(")");
        }
    }

    public static void main(String[] args) {
        System.out.println(new q606().tree2str(TreeNode.listToTree(Arrays.asList(1,2,3,4))));
    }
}