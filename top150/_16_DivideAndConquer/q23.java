package top150._16_DivideAndConquer;

import java.util.PriorityQueue;

import node_definition.ListNode;

public class q23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (a,b)->a.val-b.val);
        ListNode dummy = new ListNode();
        ListNode ptr = dummy;

        for(ListNode list: lists) {
            if(list != null) minHeap.offer(list);
        }

        while(minHeap.size() > 1) {
            ptr.next = minHeap.poll();
            ptr = ptr.next;

            if(ptr.next != null) minHeap.offer(ptr.next);
        }

        ptr.next = minHeap.poll();

        return dummy.next;
    }
}
