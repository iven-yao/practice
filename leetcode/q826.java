package leetcode;
import java.util.*;

public class q826 {
    class Solution {
        public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
            List<int[]> jobs = new ArrayList<>();
    
            for(int i = 0; i < profit.length; i++) {
                jobs.add(new int[]{difficulty[i], profit[i]});
            }
    
            Collections.sort(jobs, (a,b)->(a[0]-b[0]));
            // System.out.println(jobs);
    
            Arrays.sort(worker);
    
            int best = 0;
            int total = 0;
            int index = 0;
            for(int work: worker) {
                while(index < profit.length && jobs.get(index)[0] <= work) {
                    best = Math.max(best, jobs.get(index)[1]);
                    index++;
                }
    
                total += best;
                
            }
    
            return total;
        }
    }
}
