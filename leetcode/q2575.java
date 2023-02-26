package leetcode;

public class q2575 {
    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] ans = new int[n];
        
        long curr = 0;
        for(int i = 0; i < n; i++) {
            curr = curr * 10 + (word.charAt(i)-'0');
            curr %= m;
            
            if(curr == 0) {
                ans[i] = 1;
            }            
        }
        
        return ans;
    }
}
