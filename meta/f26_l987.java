package meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import node_definition.TreeNode;

public class f26_l987 {
    /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
    class Solution {
        public List<List<Integer>> verticalTraversal(TreeNode root) {        
            Queue<TreeNode> nodeQ = new LinkedList<>();
            Queue<Integer> colQ = new LinkedList<>();
            nodeQ.offer(root);
            colQ.offer(0);

            Map<Integer, List<Integer>> colMap = new HashMap<>();
            int leftmost = 0;
            int rightmost = 0;

            while(!nodeQ.isEmpty()) {

                int size = nodeQ.size();
                Map<Integer, PriorityQueue<Integer>> rowMap = new HashMap<>();
                for(int i = 0; i < size; i++) {
                    TreeNode curr = nodeQ.poll();
                    int col = colQ.poll();
                    
                    // update
                    leftmost = Math.min(col, leftmost);
                    rightmost = Math.max(col, rightmost);
                    colMap.putIfAbsent(col, new ArrayList<>());
                    rowMap.putIfAbsent(col, new PriorityQueue<>((a,b) -> a-b));
                    rowMap.get(col).offer(curr.val);

                    // add child to queue
                    if(curr.left != null) {
                        nodeQ.offer(curr.left);
                        colQ.offer(col-1);
                    }

                    if(curr.right != null) {
                        nodeQ.offer(curr.right);
                        colQ.offer(col+1);
                    }
                }

                for(Map.Entry<Integer, PriorityQueue<Integer>> e: rowMap.entrySet()) {
                    PriorityQueue<Integer> minHeap = e.getValue();
                    int col = e.getKey();
                    while(!minHeap.isEmpty()) {
                        colMap.get(col).add(minHeap.poll());
                    }
                }
            }

            List<List<Integer>> res = new ArrayList<>();
            for(int i = leftmost; i <= rightmost; i++) {
                res.add(colMap.get(i));
            }

            return res;
        }
    }
}
