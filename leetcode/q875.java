package leetcode;

public class q875 {

    // another binary search, the problem is how to find the boundary
    // in this problem, the least time to take is to eat argmax pile
    // amount of bananas a time, and the lower bound should be 1
    // when it's finished faster than/or equal to h, koko will prefer 
    // slowing down, so move upperbound to mid; otherwise, move lower
    // bound to mid+1, since it's too slow, and can't be finished in h.
    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        int min = 1;

        for(int pile: piles) {
            max = Math.max(max, pile);
        }
        
        while(min < max){
            int mid = min + (max-min)/2;

            int hourTakes = 0;
            for(int pile: piles) {
                hourTakes = hourTakes + (pile/mid) + ((pile%mid == 0)?0:1);
            }

            if(hourTakes <= h) {max = mid;}
            else {min = mid+1;}
        }

        return min;
    }
}
