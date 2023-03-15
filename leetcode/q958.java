package leetcode;

import java.util.*;

import node_definition.TreeNode;

public class q958 {
    public boolean isCompleteTree(TreeNode root) {
        List<TreeNode> currLevel = new ArrayList<>();
        currLevel.add(root);
        boolean full = true;
        while(!currLevel.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            boolean rightmost = true;
            boolean levelfull = true;
            while(!currLevel.isEmpty()) {
                TreeNode node = currLevel.remove(0);
                if(node == null){
                    levelfull = false;
                    if(!rightmost) return false;
                }
                if(node != null) {
                    if(!full) return false;
                    rightmost = false;
                    nextLevel.add(node.right);
                    nextLevel.add(node.left);    
                }
            }
            full = levelfull;
            currLevel = nextLevel;
        }

        return true;
    }
}
