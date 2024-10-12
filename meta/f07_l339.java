package meta;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
interface NestedInteger {
 
      // @return true if this NestedInteger holds a single integer,
      // rather than a nested list.
      public boolean isInteger();
 
      // @return the single integer that this NestedInteger holds,
      // if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      public Integer getInteger();
 
      // @return the nested list that this NestedInteger holds,
      // if it holds a nested list
      // Return null if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
}

public class f07_l339 {
    class Solution {

        public int depthSum(List<NestedInteger> nestedList) {
            // Write your code here

            Queue<NestedInteger> queue = new LinkedList<>();
            int sum = 0;
            int depth = 0;
            for(NestedInteger ni : nestedList) {
                queue.offer(ni);
            }

            
            while(!queue.isEmpty()) {
                depth++;
                int size = queue.size();

                while(size > 0) {
                    size--;
                    NestedInteger curr = queue.poll();
                    if(curr.isInteger()) {
                        sum += depth * curr.getInteger();
                    } else {
                        for(NestedInteger child: curr.getList()) {
                            queue.offer(child);
                        }
                    }
                }
            }

            return sum;
        }
    }
}
