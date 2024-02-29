package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class tiktok_oa_0213 {
    class Q3 {
        public long maxProducts(int[] cost, long budget) {
            Queue<Integer> q = new LinkedList<>();
            for(int c: cost) {
                q.offer(c);
            }

            long count = 0;

            while(budget > 0 && !q.isEmpty()) {
                int c = q.poll();
                if(c <= budget) {
                    budget -= c;
                    count++;
                }
                if(c <= budget) {
                    q.offer(c);
                }
            }

            return count;
        }
    }
    
    class Q4 {

        public int calculate(int[] input) {

            int minVal = Integer.MAX_VALUE;
            for(int i = 0; i < input.length - 2; i++) {
                int j = i+1;
                int k = input.length-1;
                int median = input[j];
                while( j < k) {
                    int mid = (j+k)/2;
                    float mean = ((float)(input[i]+input[j]+input[k]))/3;
                    int validity = Math.round(3*Math.abs(mean - median));
                    minVal = Math.min(minVal, validity);
                    if(mean > median) {
                        k = mid;
                    } else {
                        j = mid+1;
                    }
                }
            }

            return minVal;
        }

        public List<int[]> getAllSubs(int[] input) {
            List<int[]> subs = new ArrayList<>();
            Arrays.sort(input);
            for(int i = 0 ; i < input.length-2; i ++) {
                for(int j = i+1; j < input.length -1; j++) {
                    int[] tmp = new int[]{input[i],input[j], input[j+1]};
                    subs.add(tmp);
                }
            }

            return subs;
        }

        public int getValidity(int[] sub3) {
            float mean = ((float)(sub3[0] + sub3[1] + sub3[2]))/3;
            int median = sub3[1];

            return Math.round(3*Math.abs(mean - median));
        }
    }

    class Q5 {
        public int maxValue(int[] input) {
            int[] curr = Arrays.copyOf(input, input.length);
            int[] maxRes = new int[input.length];

            for(int i = 1; i < input.length; i ++) {
                for(int j = 0; j < input.length - i; j++) {
                    curr[j] ^= input[i];
                    maxRes[j] = Math.max(maxRes[j],curr[j]);
                }
            }

            int max = 0;
            for(int i = 0; i < input.length; i ++) {
                max= Math.max(max, maxRes[i]);
            }

            return max;
        }
    }

    public static void main(String[] args) {
        tiktok_oa_0213 t = new tiktok_oa_0213();
        Q3 q3 = t.new Q3();
        System.out.println("====Q3====");
        System.out.println(q3.maxProducts(new int[]{1,50}, 99));
        System.out.println("====Q4====");

        Q4 sol = t.new Q4();
        int[] input = new int[]{20,15,99,100};
        System.out.println(sol.calculate(input));
        
        Q5 q5 = t.new Q5();
        System.out.println("====Q5====");
        System.out.println(q5.maxValue(new int[]{8,2,4,12,1}));
        System.out.println(q5.maxValue(new int[]{0,2,5,1}));
        System.out.println(q5.maxValue(new int[]{12,8,24}));
    }
}
