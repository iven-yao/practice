package meta;

import java.util.PriorityQueue;

public class f19_l215 {
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->a-b);
            for(int num: nums) {
                minHeap.offer(num);
                if(minHeap.size() > k) minHeap.poll();
            }

            return minHeap.poll();
        }
    }
}
