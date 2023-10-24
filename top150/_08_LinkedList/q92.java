package top150._08_LinkedList;

import node_definition.ListNode;

public class q92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode allPrev = null;
        ListNode allNext = null;
        ListNode dum = new ListNode();
        dum.next = head;
        ListNode cur = dum;
        int idx = 0;
        while (cur != null) {
            idx ++;
            if (idx == left) {
                allPrev = cur;
            } 
            cur = cur.next;
            if (idx == right) {
                allNext = cur.next;
            }
        }

        ListNode nx = allNext;
        cur = allPrev.next;
        while (cur != allNext) {
            ListNode temp = cur.next;
            cur.next = nx;
            nx = cur;
            cur = temp;
        }
        allPrev.next = nx;

        return dum.next;
    }
}
