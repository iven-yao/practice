package top150._20_BitManipulation;

public class q137 {
    public int singleNumber(int[] nums) {
        int seenOnce = 0, seenTwice = 0;
        for (int num : nums) {
            seenOnce ^= ~seenTwice & num;
            seenTwice ^= ~seenOnce & num;
        }

        return seenOnce;    
    }
}
