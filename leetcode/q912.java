package leetcode;
import java.util.*;

public class q912 {
    public int[] heapSort(int[] nums) {
        Queue<Integer> minHeap = new PriorityQueue<>();

        // n logn
        for(int num: nums) {
            minHeap.add(num);
        }

        // n
        for(int i = 0; i < nums.length; i++) {
            nums[i] = minHeap.poll();
        }

        return nums;
    }

    public int[] bucketSort(int[] nums) {
        int[] count = new int[100001];

        for(int num: nums) {
            count[num+50000]++;
        }

        int j = 0;
        for(int i = 0; i < count.length; i++) {
            while(count[i] != 0) {
                nums[j++] = i-50000;
                count[i]--;
            }
        }

        return nums;
    }
}
