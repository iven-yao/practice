package leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
class q102 {
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();
        levelOrderHelper(root, list, 0);
        return list;
    }
    
    // DFS
    public List<List<Integer>> levelOrderHelper(TreeNode root, List<List<Integer>> list, int level){
        if(root == null) return null;
        if(list.size() == level ) list.add(new ArrayList<Integer>());
        list.get(level).add(root.val);
        if(root.left != null) {
            levelOrderHelper(root.left, list, level+1);
        }
        if(root.right!= null) {
            levelOrderHelper(root.right, list, level+1);
        } 
        
        return list;
    }

    // BFS
    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        List<TreeNode> curr = new ArrayList<>();
        if(root != null) curr.add(root);

        while(!curr.isEmpty()){
            List<TreeNode> next = new ArrayList<>();
            List<Integer> subList = new ArrayList<>();
            for(TreeNode node: curr){
                subList.add(node.val);
                if(node.left != null) next.add(node.left);
                if(node.right != null) next.add(node.right);
            }

            curr = next;
            list.add(subList);
        }

        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3,9,20,null,null,15,7));
        System.out.println(new q102().levelOrderDFS(TreeNode.listToTree(list)));
        System.out.println(new q102().levelOrderBFS(TreeNode.listToTree(list)));
    }

}