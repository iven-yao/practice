package leetcode;

import java.util.*;
import node_definition.TreeNode;

public class q257 {
    List<String> res;
    public List<String> binaryTreePaths(TreeNode root) {
        res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        pathHelper(root, sb);

        return res;
    }

    public void pathHelper(TreeNode node, StringBuilder sb) {
        int size = sb.length();
        if(node == null) return;
        if(node.left == null && node.right == null) {
            sb.append(node.val);
            res.add(sb.toString());
            sb.delete(size, sb.length());
        }
        sb.append(node.val).append("->");
        pathHelper(node.left, sb);
        pathHelper(node.right, sb);
        sb.delete(size, sb.length());
    }
}
