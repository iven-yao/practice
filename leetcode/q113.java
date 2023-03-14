package leetcode;

import java.util.*;
import node_definition.TreeNode;

public class q113 {
    List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        sumHelper(root, new ArrayList<>(), 0, targetSum);
        return res;
    }

    public void sumHelper(TreeNode node, List<Integer> list, int val, int target) {
        if(node == null) return;
        if(node.left == null  && node.right == null) {
            if(val+node.val == target) {
                list.add(node.val);
                res.add(new ArrayList<>(list));
                list.remove(list.size()-1);
            }
        }

        list.add(node.val);
        val = val+node.val;
        sumHelper(node.left, list, val, target);
        sumHelper(node.right, list, val, target);
        list.remove(list.size()-1);
    }
}
