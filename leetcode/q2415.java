package leetcode;
import node_definition.TreeNode;
import java.util.*;

public class q2415 {

    // 1ms
    public TreeNode reverseOddLevels(TreeNode root) {
        switchVals(root.left, root.right, true);
        
        return root;
    }

    public void switchVals(TreeNode left, TreeNode right, boolean isOdd) {
        if(left == null || right == null) return;
        
        if(isOdd) {
            int tmp = left.val;
            left.val = right.val;
            right.val = tmp;
        }
        switchVals(left.left, right.right, !isOdd);
        switchVals(left.right, right.left, !isOdd);
    }

    // 20ms
    public TreeNode reverseOddLevelsSLOW(TreeNode root) {
        
        boolean isEven = true;
        Queue<TreeNode> currLevel = new LinkedList<>();
        currLevel.add(root);

        while(currLevel.size() > 0) {
            Queue<TreeNode> nextLevel = new LinkedList<>();
            Stack<Integer> vals = new Stack<>();
            while(currLevel.size() > 0) {
                TreeNode node = currLevel.remove();
                if(node.left != null) {
                    nextLevel.add(node.left);
                    vals.push(node.left.val);
                } else break;
                if(node.right != null) {
                    nextLevel.add(node.right);
                    vals.push(node.right.val);
                } else break;
            }
            if(isEven) {
                //next level value should be reversed:
                for(TreeNode node: nextLevel) {
                    node.val = vals.pop();
                }
            }

            currLevel = nextLevel;
            isEven = !isEven;
        }
        
        return root;
    }
}
