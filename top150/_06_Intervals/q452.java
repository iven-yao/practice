package top150._06_Intervals;

import java.util.Arrays;

public class q452 {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a,b)->Integer.compare(a[1],b[1]));
        int index = 1;
        int res = 1;
        int end = points[0][1];
        while(index < points.length) {
            if(points[index][0] > end) {
                end = points[index][1];
                res++;
            }
            index++;
        }

        return res;
    }
}
