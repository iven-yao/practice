package leetcode;
import java.util.*;
import node_definition.TreeNode;

public class q106 {

    // improve time complexity by store index to map in one run, instead of looking up for index everytime
    class Solution {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            Map<Integer, Integer> indexMap = new HashMap<>();
            for(int i = 0; i < inorder.length; i++) {
                indexMap.put(inorder[i], i);
            }
            return buildTree(indexMap, inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
        }
    
        public TreeNode buildTree(Map<Integer, Integer> indexMap, int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd) {
            if(iStart > iEnd || pStart > pEnd) return null;
    
            TreeNode node = new TreeNode(postorder[pEnd]);
    
            // int index = 0;
            // for(int i = iStart; i <= iEnd; i++) {
            //     if(inorder[i] == postorder[pEnd]) index = i;
            // }
    
            int index = indexMap.get(postorder[pEnd]);
    
            node.left = buildTree(indexMap, inorder, iStart, index-1, postorder, pStart, index-iStart-1+pStart);
            node.right = buildTree(indexMap, inorder, index+1, iEnd, postorder, index-iStart+pStart, pEnd-1);
    
            return node;
        }
    }

    class Solution1 {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return buildTree(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
        }
    
        public TreeNode buildTree(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd) {
            if(iStart > iEnd || pStart > pEnd) return null;
    
            TreeNode node = new TreeNode(postorder[pEnd]);
    
            int index = 0;
            for(int i = iStart; i <= iEnd; i++) {
                if(inorder[i] == postorder[pEnd]) index = i;
            }
    
            node.left = buildTree(inorder, iStart, index-1, postorder, pStart, index-iStart-1+pStart);
            node.right = buildTree(inorder, index+1, iEnd, postorder, index-iStart+pStart, pEnd-1);
    
            return node;
        }
    }


}
