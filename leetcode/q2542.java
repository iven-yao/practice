package leetcode;
import java.util.*;

public class q2542 {
    class Solution {
        public long maxScore(int[] nums1, int[] nums2, int k) {
            int n = nums1.length;
            int[][] pairs = new int[n][2];
    
            for(int i = 0; i < n; i++) {
                pairs[i][0] = nums2[i];
                pairs[i][1] = nums1[i];
            }
    
            Arrays.sort(pairs, (a,b) -> b[0]-a[0]);
            
            long sum = 0;
            long res = 0;
            // min heap
            Queue<Integer> q = new PriorityQueue<Integer>(k+1);
            for(int[] pair: pairs) {
                sum += pair[1];
                q.offer(pair[1]);
                if(q.size() > k) sum -= q.poll();
                if(q.size() == k) res = Math.max(res, sum*pair[0]);
            }
    
            return res;
        }
    }
}
