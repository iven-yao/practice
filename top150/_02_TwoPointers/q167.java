package top150._02_TwoPointers;

public class q167 {
    public int[] twoSum(int[] numbers, int target) {
        int head = 0;
        int tail = numbers.length-1;

        while(head < tail) {
            int addup = numbers[head] + numbers[tail];

            if(addup > target) tail--;
            if(addup < target) head++;
            if(addup == target) return new int[]{head+1, tail+1};
        }

        return new int[]{0,0};
    }
}
