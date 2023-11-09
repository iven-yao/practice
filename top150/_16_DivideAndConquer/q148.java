package top150._16_DivideAndConquer;

import java.util.LinkedList;
import java.util.Queue;

import node_definition.ListNode;

public class q148 {
    public ListNode sortList(ListNode head) {
        Queue<ListNode> q = new LinkedList<>();
        ListNode ptr = head;
        while(ptr != null) {
            ListNode curr = ptr;
            ptr = ptr.next;
            curr.next = null;
            q.offer(curr);
        }

        while(q.size() > 1) {
            q.offer(merge(q.poll(), q.poll()));
        }

        return q.poll();
    }

    private ListNode merge(ListNode n1, ListNode n2) {
        ListNode dummy = new ListNode();
        ListNode ptr = dummy;
        while(n1 != null && n2 != null) {
            if(n1.val < n2.val) {
                ptr.next = n1;
                n1 = n1.next;
            } else {
                ptr.next = n2;
                n2 = n2.next;
            }
            ptr = ptr.next;
        }

        if(n1 != null) ptr.next = n1;
        if(n2 != null) ptr.next = n2;

        return dummy.next;
    }
}
