import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import node_definition.TreeNode;

public class q094 {
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return inorderHelper(root, list);
    }
    
    public List<Integer> inorderHelper(TreeNode node, List<Integer> list) {
        if(node != null) {
            if(node.left != null) {
                inorderHelper(node.left, list);
            }
            list.add(node.val);
            if(node.right != null) {
                inorderHelper(node.right, list);
            }
        }
        return list;
    }
    
    public static void main(String[] args){
        System.out.println(new q094().inorderTraversal(TreeNode.listToTree(Arrays.asList(1, null,2,null, null, 3))));
    }
}
