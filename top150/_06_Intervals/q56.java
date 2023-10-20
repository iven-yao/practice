package top150._06_Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);
        List<int[]> res = new ArrayList<>();
        int[] prevInterval = intervals[0];
        for(int[] interval: intervals){
            if(interval[0] <= prevInterval[1]) {
                prevInterval[1] = Math.max(prevInterval[1], interval[1]);
            } else {
                res.add(prevInterval);
                prevInterval = interval;
            }
        }

        res.add(prevInterval);

        return res.toArray(new int[res.size()][]);
    }
}
