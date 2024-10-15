package meta;

import java.util.PriorityQueue;

public class f31_l973 {
    class Solution {
        public int[][] kClosest(int[][] points, int k) {

            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)-> -(b[0]*b[0]+b[1]*b[1]) + (a[0]*a[0]+a[1]*a[1]));
            for(int[] point: points) {
                minHeap.offer(point);
            }

            int[][] res = new int[k][2];
            for(int i = 0; i < k; i++) {
                res[i] = minHeap.poll();
            }

            return res;
        }
    }
}
