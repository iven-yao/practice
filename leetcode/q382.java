package leetcode;
import java.util.*;
import node_definition.ListNode;

public class q382 {
    class Solution {

        List<Integer> list;
        int n;
        Random rand;
        public Solution(ListNode head) {
            rand = new Random();
            list = new ArrayList<>();
            n = 0;
            while(head != null) {
                n++;
                list.add(head.val);
                head = head.next;
            }
        }
        
        public int getRandom() {
            return list.get(rand.nextInt(n));
        }
    }
}
