package top150._19_Heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class q215 {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> maxHeap = new PriorityQueue<>(nums.length, (a, b)->b-a);

        for(int num: nums) maxHeap.offer(num);
        while(k > 1) {
            maxHeap.poll();
            k--;
        }

        return maxHeap.poll();
    }
}
