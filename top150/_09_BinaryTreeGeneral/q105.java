package top150._09_BinaryTreeGeneral;

import java.util.HashMap;

import node_definition.TreeNode;

public class q105 {
    private int i = 0;
    private int p = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (p >= preorder.length) {
            return null;
        }
        if (inorder[i] == stop) {
            ++i;
            return null;
        }

        TreeNode node = new TreeNode(preorder[p++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);
        return node;
    }

    class slowerWithHashMap{
        private int preorderIdx = 0;
        private TreeNode construct(int[] preorder, HashMap<Integer,Integer> map, int left, int right){
            if(left>right) return null;
            int pval = preorder[preorderIdx];
            int inorderIdx = map.get(pval);

            TreeNode root = new TreeNode(pval);
            preorderIdx++;
            root.left = construct(preorder, map, left, inorderIdx-1);
            root.right = construct(preorder, map, inorderIdx+1, right);
            return root;
            
        }
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            HashMap<Integer,Integer> map = new HashMap<>();
            int len = inorder.length;
            for(int i=0;i<len;i++){
                map.put(inorder[i],i);
            }

            return construct(preorder, map, 0, len-1);
        }
    }
}
