package leetcode;
import java.util.*;

public class q347 {
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> count = new HashMap<>();
            
            for(int num: nums) {
                int c = count.computeIfAbsent(num, a->0);
                count.put(num, c+1);
            }
    
            Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1]-a[1]);
    
            for(int key: count.keySet()) {
                maxHeap.offer(new int[]{key, count.get(key)});
            }
            // for better performance, we can use bucket sort instead of heap sort
    
            int[] topK = new int[k];
    
            for(int i = 0; i < k; i++) {
                topK[i] = maxHeap.poll()[0];
            }
    
            return topK;
        }
    }
}
