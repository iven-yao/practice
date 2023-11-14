package top150._19_Heap;

import java.util.PriorityQueue;

public class q295 {
    class MedianFinder {
        private PriorityQueue<Integer> minHeap;
        private PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            minHeap = new PriorityQueue<>((a,b) -> a-b);
            maxHeap = new PriorityQueue<>((a,b) -> b-a);
        }
        
        public void addNum(int num) {
            if(maxHeap.size() == 0 || maxHeap.peek() >= num) maxHeap.offer(num);
            else minHeap.offer(num);
            balance();
        }

        public void balance() {
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if(maxHeap.size() == minHeap.size())
                return (double)(minHeap.peek()+maxHeap.peek())/2.0;
            PriorityQueue<Integer> pq = maxHeap.size()>minHeap.size()? maxHeap:minHeap;
            return pq.peek();
        }
    }
}
