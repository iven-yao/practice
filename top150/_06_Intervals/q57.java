package top150._06_Intervals;

public class q57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int l = 0;
        int r = n-1;

        while(l < n && intervals[l][1] < newInterval[0]) l++;
        while(r >= 0 && intervals[r][0] > newInterval[1]) r--;

        int[][] res = new int[n-r+l][2];

        for(int i = 0; i < l; i++) {
            res[i] = intervals[i];
        }
        res[l][0] = Math.min(l == n ? newInterval[0]:intervals[l][0], newInterval[0]);
        res[l][1] = Math.max(r == -1 ? newInterval[1]:intervals[r][1], newInterval[1]);
        for(int i = l+1, j = r+1; j < n; i++, j++) {
            res[i] = intervals[j];
        }

        return res;
    }
}
