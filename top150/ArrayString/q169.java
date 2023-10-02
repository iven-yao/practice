package top150.ArrayString;
// 169. Majority Element
public class q169 {
    public int majorityElement(int[] nums) {
        int majority = 0;
        int count = 0;

        for(int ele: nums) {
            if(count == 0) {
                majority = ele;
                count++;
            } else if(ele == majority) {
                count++;
            } else {
                count--;
            }
        }

        return majority;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1,1,1,2,2};

        int res = new q169().majorityElement(nums);
        System.out.println(res);
    }
}
