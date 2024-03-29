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

    class Solution1 {

        int[] arr;
        int n;
        Random rand;
        public Solution1(ListNode head) {
            rand = new Random();
            n = 0;
            ListNode curr = head;
            while(curr != null) {
                n++;
                curr = curr.next;
            }
    
            arr = new int[n];
            n = 0;
            while(head != null) {
                arr[n] = head.val;
                head = head.next;
                n++; 
            }
        }
        
        public int getRandom() {
            return arr[rand.nextInt(n)];
        }
    }
}
