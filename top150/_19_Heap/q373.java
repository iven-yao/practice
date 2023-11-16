package top150._19_Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class q373 {

    class better{
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            List<List<Integer>> res = new ArrayList<>(); // Result list to store the pairs
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0]+a[1] - b[0]-b[1]);
            // Priority queue to store pairs with smallest sums, sorted by the sum
    
            // Push the initial pairs into the priority queue
            for (int num : nums1) {
                minHeap.offer(new int[]{num, nums2[0], 1}); // The sum and the next index in nums2
            }
    
            // Pop the k smallest pairs from the priority queue
            while (k > 0 && !minHeap.isEmpty()) {
                int[] pair = minHeap.poll();
    
                res.add(Arrays.asList(pair[0], pair[1])); // Add the pair to the result list
    
                // If there are more elements in nums2, push the next pair into the priority queue
                if (pair[2] < nums2.length) {
                    minHeap.offer(new int[]{pair[0], nums2[pair[2]], pair[2]+1});
                }
    
                k--;
            }
    
            return res;
        }
        
    }


    class slow{
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        
            // build min heap
            Queue<List<Integer>> maxHeap = new PriorityQueue<>(k, (a,b)->(sum(b) - sum(a)));

            for(int num1 : nums1) {
                for(int num2 : nums2) {
                    if(maxHeap.size() < k) {
                        List<Integer> l = Arrays.asList(num1, num2);
                        maxHeap.offer(l);
                    } else if(num1 + num2 < sum(maxHeap.peek())) {
                        List<Integer> l = Arrays.asList(num1, num2);
                        maxHeap.poll();
                        maxHeap.offer(l);
                    } else break;
                }
            }

            // extract k times from min heap
            List<List<Integer>> res = new ArrayList<>();
            while(!maxHeap.isEmpty()) {
                res.add(maxHeap.poll());
            }

            return res;
        }

        private int sum(List<Integer> list) {
            int sum = 0;
            for(int num: list) sum += num;

            return sum;
        }
    }
}
