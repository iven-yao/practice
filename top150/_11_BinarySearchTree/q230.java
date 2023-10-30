package top150._11_BinarySearchTree;

import node_definition.TreeNode;

public class q230 {
    int kth = 0;
    int res = -1;
    public int kthSmallest(TreeNode root, int k) {
        if(root.left != null) kthSmallest(root.left, k);
        kth++;
        if(kth == k) res = root.val;
        if(root.right != null) kthSmallest(root.right, k);

        return res;
    }
}
