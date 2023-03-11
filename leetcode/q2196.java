package leetcode;
import java.util.*;
import node_definition.TreeNode;
public class q2196 {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, int[]> p2c = new HashMap<>();
        Map<Integer, Integer> c2p = new HashMap<>();
        int dummyRoot = descriptions[0][1];
        for(int[] des : descriptions) {
            p2c.computeIfAbsent(des[0], f -> new int[2]);
            p2c.get(des[0])[des[2]] = des[1];
            c2p.put(des[1],des[0]);
        }

        while(c2p.get(dummyRoot) != null) {
            dummyRoot = c2p.get(dummyRoot);
        }

        return helper(p2c, dummyRoot);
    }

    public TreeNode helper(Map<Integer, int[]> p2c, int val) {
        TreeNode node = new TreeNode(val);
        if(p2c.get(val) != null) {
            int[] child = p2c.get(val);
            if(child[0] != 0) node.right = helper(p2c, child[0]);
            if(child[1] != 0) node.left = helper(p2c, child[1]);
        }

        return node;
    }
    
}
