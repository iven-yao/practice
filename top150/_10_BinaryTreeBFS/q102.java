package top150._10_BinaryTreeBFS;

import java.util.ArrayList;
import java.util.List;

import node_definition.TreeNode;

public class q102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);

        while(!curr.isEmpty()) {
            
            List<Integer> list = new ArrayList<>();
            List<TreeNode> next = new ArrayList<>();

            for(TreeNode node: curr) {
                list.add(node.val);
                if(node.left != null) next.add(node.left);
                if(node.right != null) next.add(node.right);
            }

            res.add(list);
            curr = next;
        }

        return res;
    }
}
