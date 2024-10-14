package meta;

import node_definition.TreeNode;

public class f24_l938 {
    class Solution {
        public int rangeSumBST(TreeNode root, int low, int high) {
            if(root == null) return 0;
    
            if(root.val > high) return rangeSumBST(root.left, low, high);
            else if(root.val < low) return rangeSumBST(root.right, low, high);
            else return rangeSumBST(root.left, low, high) + root.val + rangeSumBST(root.right, low, high);
        }
    }
}
