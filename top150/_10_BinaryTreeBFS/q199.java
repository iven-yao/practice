package top150._10_BinaryTreeBFS;

import java.util.ArrayList;
import java.util.List;

import node_definition.TreeNode;

public class q199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);

        while(!curr.isEmpty()) {

            TreeNode rightmost = null;
            List<TreeNode> next = new ArrayList<>();
            for(TreeNode node: curr) {
                rightmost = node;
                if(node.left != null) next.add(node.left);
                if(node.right != null) next.add(node.right);
            }

            if(rightmost != null) res.add(rightmost.val);
            curr = next;
        }

        return res;
    }
}
