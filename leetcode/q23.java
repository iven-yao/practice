package leetcode;
import java.util.*;
import node_definition.*;

public class q23 {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<Integer> minHeap = new PriorityQueue<>();

        for(ListNode node: lists) {
            while(node != null) {
                minHeap.offer(node.val);
                node = node.next;
            }
        }

        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while(!minHeap.isEmpty()) {
            curr.next = new ListNode(minHeap.poll());
            curr = curr.next;
        }

        return dummy.next;
        
    }
}
