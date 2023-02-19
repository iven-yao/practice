package leetcode;
import node_definition.TreeNode;
import java.util.*;

public class q103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<TreeNode> curr = new Stack<>();
        boolean leftToRight =true;
        if(root == null) return res;
        curr.push(root);

        while(!curr.isEmpty()) {
            Stack<TreeNode> next = new Stack<>();
            List<Integer> sublist = new ArrayList<>();
            if(leftToRight) {
                while(!curr.isEmpty()) {
                    TreeNode node = curr.pop();
                    sublist.add(node.val);
                    if(node.left != null) next.push(node.left);
                    if(node.right != null) next.push(node.right);
                }
            } else {
                while(!curr.isEmpty()) {
                    TreeNode node = curr.pop();
                    sublist.add(node.val);
                    if(node.right != null) next.push(node.right);
                    if(node.left != null) next.push(node.left);
                }
            }
            res.add(sublist);
            curr = next;
            leftToRight = !leftToRight;
        }

        return res;
    }
}
