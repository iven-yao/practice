package top150._08_LinkedList;

import node_definition.ListNode;

public class q19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;
        ListNode ptr = head;
        while( ptr != null) {
            size++;
            ptr = ptr.next;
        }

        if(size == n) return head.next;

        int index = 0;
        ptr = head;
        while(ptr != null) {
            if(index == size - n -1) ptr.next = ptr.next.next;
            ptr = ptr.next;
            index++;
        }

        return head;
    }
}
