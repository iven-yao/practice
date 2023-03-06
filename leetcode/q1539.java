package leetcode;

public class q1539 {
    public int findKthPositive(int[] arr, int k) {
        int i = 0;
        for(int num: arr){
            while(k>0) {
                if(++i < num){
                    k--;
                } else break;
            }

            if(k == 0) return i;
        }

        return i+k;
    }
}
