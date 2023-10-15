package top150._01_ArrayString;

public class q42 {
    public int trap(int[] height) {
        int n = height.length;
        int[] front = new int[n];
        int[] back = new int[n];

        int currMax = 0;
        for(int i = 0; i < n; i++) {
            currMax = Math.max(currMax, height[i]);
            front[i] = currMax;
        }

        currMax = 0;
        for(int i = n-1; i>=0; i--) {
            currMax = Math.max(currMax, height[i]);
            back[i] = currMax;
        }

        int water = 0;
        for(int i = 0; i < n; i++) {
            water += Math.min(front[i],back[i]) - height[i];
        }

        return water;
    }
}
