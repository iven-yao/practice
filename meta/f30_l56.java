package meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class f30_l56 {
    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (a,b)->a[0] - b[0]);
            List<int[]> merged = new ArrayList<>();

            merged.add(intervals[0]);
            for(int i = 1; i < intervals.length; i++) {
                int[] prev = merged.get(merged.size()-1);
                if(prev[1] >= intervals[i][0]) {
                    prev[1] = Math.max(prev[1], intervals[i][1]);
                } else {
                    merged.add(intervals[i]);
                }
            }

            int[][] res = new int[merged.size()][2];
            for(int i = 0; i < merged.size(); i++) {
                res[i] = merged.get(i);
            }

            return res;
        }
    }
}
