package top150._08_LinkedList;

import node_definition.ListNode;

public class q61 {
    public ListNode rotateRight(ListNode head, int k) {
        int size = 0;
        ListNode ptr = head;

        while(ptr != null) {
            size++;
            ptr = ptr.next;
        }

        if(size == 0) return head;

        int rotate = k % size;
        if(rotate == 0) return head;

        int index = 0;
        ptr = head;
        ListNode newTail = null;
        ListNode newHead = null;
        ListNode tail = null;
        while(ptr != null) {
            if(index == size-rotate-1) newTail = ptr;
            if(index == size-rotate) newHead = ptr;
            if(index == size-1) tail = ptr;
            ptr = ptr.next;
            index++;
        }

        tail.next = head;
        newTail.next = null;

        return newHead;
    }
}
