package top150._10_BinaryTreeBFS;

import java.util.ArrayList;
import java.util.List;

import node_definition.TreeNode;

public class q637 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if(root == null) return res;

        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);

        while(!curr.isEmpty()) {
            double sum = 0;
            int count = 0;
            List<TreeNode> next = new ArrayList<>();

            for(TreeNode node: curr) {
                sum += node.val;
                count++;
                if(node.left != null) next.add(node.left);
                if(node.right != null) next.add(node.right);
            }

            res.add(sum/count);
            curr = next;
        }

        return res;
    }
}
