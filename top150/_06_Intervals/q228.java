package top150._06_Intervals;

import java.util.ArrayList;
import java.util.List;

public class q228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums.length == 0) return res;

        int rangeStart = nums[0];
        int rangeEnd = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == rangeEnd+1) {
                rangeEnd = nums[i];
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(rangeStart);
                if(rangeEnd != rangeStart) {
                    sb.append("->").append(rangeEnd);
                }
                res.add(sb.toString());

                rangeStart = nums[i];
                rangeEnd = nums[i];
            }
        }

        // last one
        StringBuilder sb = new StringBuilder();
        sb.append(rangeStart);
        if(rangeEnd != rangeStart) {
            sb.append("->").append(rangeEnd);
        }
        res.add(sb.toString());

        return res;
    }
}
