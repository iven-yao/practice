package leetcode;
import java.util.PriorityQueue;
import java.util.Queue;

public class q703 {
    class KthLargest {
        int cap;
        Queue<Integer> minHeap;
        public KthLargest(int k, int[] nums) {
            cap = k;
            minHeap = new PriorityQueue<>((a,b)->a-b);
    
            for(int num: nums) {
                minHeap.offer(num);
    
                if(minHeap.size() > cap) {
                    minHeap.poll();
                }
            }
        }
        
        public int add(int val) {
            minHeap.offer(val);
            if(minHeap.size() > cap) {
                minHeap.poll();
            }
    
            return minHeap.peek();
        }
    }
    
    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     */
}
