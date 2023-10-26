package top150._09_BinaryTreeGeneral;

import node_definition.TreeNode;

public class q106 {
    private int i = 0;
    private int p = 0;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        i = inorder.length-1;
        p = postorder.length-1;
        return build(inorder, postorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] inorder, int[] postorder, int stop) {
        if(p < 0) return null;
        if(inorder[i] == stop) {
            i--;
            return null;
        }

        TreeNode node = new TreeNode(postorder[p--]);
        node.right = build(inorder, postorder, node.val);
        node.left = build(inorder, postorder, stop);
        return node;
    }
}
