package leetcode;

public class q2187 {
    public long minimumTime(int[] time, int totalTrips) {
        long tl=Long.MAX_VALUE;
        long tr=0;
        long min=Long.MAX_VALUE;
        for(int it:time){
            tl=Math.min(tl,it);
            min=Math.min(min,it);
        }
        tr=totalTrips*min;

        long trip;
        while(tl < tr) {
            long t = tl+ (tr-tl)/2;
            trip = 0;
            for(int num: time) {
                trip += t/num;
            }

            if(trip < totalTrips) {
                tl = t+1;
            } else {
                tr = t;
            }
        }

        return tl;
    }
}
