package top150._21_Math;

public class q172 {
    public int trailingZeroes(int n) {
        if(n == 0) return 0;
        int count = 0;
        int fives = 5;
        while(n >= fives){
            count += n/fives;
            fives *= 5;
        }
        
        return count;
    }
}
