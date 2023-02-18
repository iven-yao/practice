package leetcode;
import node_definition.TreeNode;
import java.util.*;

public class q105 {
    int currptr = 0;
    Map<Integer, Integer> cache = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i = 0; i < inorder.length; i++) {
            cache.put(inorder[i], i);
        }

        return buildTree(0, inorder.length-1, preorder);
    }

    public TreeNode buildTree(int left, int right, int[] preorder) {
        if(currptr >= preorder.length) return null;
        if(left > right) return null;
        TreeNode node = new TreeNode(preorder[currptr++]);
        int indexOfNode = cache.get(node.val);
        node.left = buildTree(left, indexOfNode-1, preorder);
        node.right = buildTree(indexOfNode+1, right, preorder);

        return node;
    }

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] preorder = {3,9,20,15,7};

        System.out.println(new q105().buildTree(preorder, inorder));
    }
}
