package leetcode;

public class q1011 {
    public int shipWithinDays(int[] weights, int days) {
        int cap = max(weights);
        if(days == weights.length) return cap;
        int max = sum(weights);

        while(cap < max) {
            int mid = (cap+(max-cap)/2);
            int load = 0;
            int day = 1;
            for(int w: weights) {
                load+= w;
                if(load > mid) {
                    load = w;
                    day++;
                }
            }

            if(day <= days) {
                max = mid;
            } else {
                cap = mid+1;
            }
        }

        return cap;
    }

    public int sum(int[] weights) {
        int sum = 0;
        for(int weight: weights) {
            sum += weight;
        }

        return sum;
    }

    public int max(int[] weights) {
        int max = Integer.MIN_VALUE;
        for(int weight: weights) {
            max = Math.max(max, weight);
        }

        return max;
    }
}
