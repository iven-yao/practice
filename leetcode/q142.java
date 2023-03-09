package leetcode;
import node_definition.ListNode;

public class q142 {

    // after first while, slow and fast meet at some point in the cycle, but not sure if it's the cycle starting point
    // we need to put the slow pointer back to the head and start moving in same speed, so they will meet at the right
    // position
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        do {
            if(fast == null || fast.next==null) { return null;}
            fast = fast.next.next;
            slow = slow.next;
        } while(slow != fast);

        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
