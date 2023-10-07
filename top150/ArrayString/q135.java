package top150.ArrayString;

public class q135 {
    public int candy(int[] ratings) {
        int n = ratings.length;
        if(n < 2) return n;

        int candy = n;
        int[] front = new int[n];
        int[] back = new int[n];

        for(int i = 1; i < n; i++) {
            if(ratings[i] > ratings[i-1]) front[i] = front[i-1] +1;
        }

        for(int i = n-1; i > 0; i--) {
            if(ratings[i-1] > ratings[i]) back[i-1] = back[i]+1;
        }

        for(int i = 0; i < n; i++) {
            candy += Math.max(front[i], back[i]); 
        }

        return candy;
    }
}
