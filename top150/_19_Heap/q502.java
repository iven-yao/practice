package top150._19_Heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class q502 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        
        // build a min heap to record all pair of (capital, profit), sort by capital
        // Queue<int[]> minHeap = new PriorityQueue<>(profits.length, (a, b)-> a[0]-b[0]);
        // for(int i = 0; i < profits.length; i++) {
        //     minHeap.offer(new int[]{capital[i], profits[i]});
        // }

        // build an 2d array and sorted by capital
        int[][] cp = new int[profits.length][2];
        for(int i = 0; i < profits.length; i++) {
            cp[i][0] = capital[i];
            cp[i][1] = profits[i];
        }

        Arrays.sort(cp, (a, b) -> a[0]-b[0]);

        Queue<Integer> maxHeap = new PriorityQueue<>(profits.length, (a, b) -> b-a);
        int index = 0;
        while( k > 0) {
            // add doable to maxHeap
            while(index < cp.length && cp[index][0] <= w) {
                maxHeap.offer(cp[index][1]);
                index++;
            }

            // pick one project
            if(maxHeap.isEmpty()) break;
            w += maxHeap.poll();
            k--;
        }

        return w;
    }
}
