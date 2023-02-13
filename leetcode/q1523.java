package leetcode;

public class q1523 {
    public int countOdds(int low, int high) {
        return (high-low)/2 + ((low %2 == 1 || high % 2 == 1)?1:0);
    }
}