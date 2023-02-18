package leetcode;
import java.util.*;
import node_definition.TreeNode;

public class q783 {
    List<Integer> orderedElem = new ArrayList<>();

    public int minDiffInBST(TreeNode root) {
        traverseBST(root);
        int min = Integer.MAX_VALUE;
        int tmp = orderedElem.get(0);
        for(int i = 1; i < orderedElem.size(); i++) {
            min = Math.min(min, orderedElem.get(i)-tmp);
            tmp = orderedElem.get(i);
        }

        return min;
    }

    public void traverseBST(TreeNode node) {
        
        if(node.left != null) {
            traverseBST(node.left);
        }
        orderedElem.add(node.val);
        if(node.right != null) {
            traverseBST(node.right);
        }
    }

}
