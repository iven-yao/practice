package top150._03_SlidingWindow;

public class q209 {

    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int head = 0;
        for(int tail = 0; tail < nums.length; tail++) {
            sum += nums[tail];
            while(sum >= target) {
                min = Math.min(min, tail-head+1);
                sum -= nums[head];
                head++;
            }
        }

        return min == Integer.MAX_VALUE? 0: min;
    }

    // public int minSubArrayLen(int target, int[] nums) {
    //     int head = 0;
    //     int tail = 1;
    //     int min = Integer.MAX_VALUE;
    //     int sum = nums[head];
    //     while( head < nums.length && tail <= nums.length) {
    //         if(sum < target && tail != nums.length) {
    //             sum += nums[tail++];
    //         } else if(sum >= target){
    //             min = Math.min(tail-head, min);
    //             sum -= nums[head++];
    //         } else {
    //             break;
    //         }
    //     }

    //     if(sum >= target) {
    //         min = Math.min(tail-head, min);
    //     }

    //     return min==Integer.MAX_VALUE?0:min;
    // }
}
