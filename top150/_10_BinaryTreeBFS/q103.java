package top150._10_BinaryTreeBFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import node_definition.TreeNode;

public class q103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);

        boolean leftToRight = true;

        while(!curr.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            List<TreeNode> next = new ArrayList<>();

            for(TreeNode node: curr) {
                list.add(node.val);
                if(node.left != null) next.add(node.left);
                if(node.right != null) next.add(node.right);
            }

            if(!leftToRight) {
                Collections.reverse(list);
            }

            res.add(list);
            curr = next;
            leftToRight = !leftToRight;
        }

        return res;
    }
}
