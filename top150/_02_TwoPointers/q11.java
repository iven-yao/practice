package top150.TwoPointers;

public class q11 {
    public int maxArea(int[] height) {
        int head = 0;
        int tail = height.length - 1;

        int max = 0;
        while(head < tail) {
            max = Math.max(max, Math.min(height[head], height[tail]) * (tail-head));
            if(height[head] > height[tail]) tail--;
            else head++;
        }

        return max;

    }
}
