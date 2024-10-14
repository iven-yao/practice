package meta;

import java.util.LinkedList;
import java.util.Queue;

public class f16_l346 {
    class MovingAverage {
        Queue<Integer> q;
        int qSize;
        double currSum;
        /*
        * @param size: An integer
        */public MovingAverage(int size) {
            // do intialization if necessary

            qSize = size;
            currSum = 0;
            q = new LinkedList<>();
        }

        /*
        * @param val: An integer
        * @return:  
        */
        public double next(int val) {
            // write your code here
            if(q.size() == qSize) {
                currSum -= q.poll();
            }

            q.offer(val);
            currSum += val;
            return currSum/q.size();
        }
    }
}
