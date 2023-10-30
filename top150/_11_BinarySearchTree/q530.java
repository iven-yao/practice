package top150._11_BinarySearchTree;

import java.util.PriorityQueue;
import java.util.Queue;

import node_definition.TreeNode;

public class q530 {

    class Solution {
        int min = Integer.MAX_VALUE;
        TreeNode prev = null;
        public int getMinimumDifference(TreeNode root) {
            if(root != null) {
                getMinimumDifference(root.left);
                if(prev != null) min = Math.min(min, Math.abs(prev.val - root.val));
                prev = root;
                getMinimumDifference(root.right);
            }
            return min;
        }
    }

    public int getMinimumDifference(TreeNode root) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        helper(root, minHeap);
        int prev = minHeap.poll();
        int min = Integer.MAX_VALUE;
        while(!minHeap.isEmpty()){
            min = Math.min(min, Math.abs(minHeap.peek() - prev));
            prev = minHeap.poll();
        }

        return min;
    }

    public void helper(TreeNode node, Queue<Integer> minHeap) {
        if(node == null) return;
        minHeap.offer(node.val);
        helper(node.right, minHeap);
        helper(node.left, minHeap);
    }
}
