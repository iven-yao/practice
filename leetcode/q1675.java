package leetcode;
import java.util.*;

// hard
public class q1675 {
    class Solution {
        public int minimumDeviation(int[] nums) {
            Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            int max = 0;
            int min = Integer.MAX_VALUE;
            int minDev = Integer.MAX_VALUE;
    
            for(int num : nums) {
                if(num%2 == 1) num = num*2;
                maxHeap.add(num);
                min = Math.min(min, num);
            }
    
            minDev = maxHeap.peek()-min;
    
            while(maxHeap.peek()%2==0) {
                int tmp = maxHeap.poll()/2;
                maxHeap.add(tmp);
                max = maxHeap.peek();
                min = Math.min(min, tmp);
                minDev = Math.min(max-min, minDev);
            }
    
            return minDev;
    
        }
    }
}
