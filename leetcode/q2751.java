package leetcode;

public class q2751 {
    public int minOperations(int n) {
        
        int count = 0;
        while(n != 0) {
            int log = log2(n);
            // System.out.println(log);
            n = Math.abs(n-(int)Math.pow(2, log));
            // System.out.println("*****"+n);
            count++;
        }
        
        return count;
    }
    
    public static int log2(int N)
    {
 
        // calculate log2 N indirectly
        // using log() method
        int result = (int)Math.round(Math.log(N) / Math.log(2));
 
        return result;
    }
}
