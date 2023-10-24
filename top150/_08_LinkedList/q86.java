package top150._08_LinkedList;

import node_definition.ListNode;

public class q86 {
    public ListNode partition(ListNode head, int x) {
        ListNode notLessThan = new ListNode();
        ListNode lessThan = new ListNode();;
        ListNode ptr = head;
        ListNode ptrNLT = notLessThan;
        ListNode ptrLT = lessThan;
        while(ptr != null) {
            if(ptr.val < x) {
                ptrLT.next = ptr;
                ptrLT = ptrLT.next;
            } else {
                ptrNLT.next = ptr;
                ptrNLT = ptrNLT.next;
            }
            ptr = ptr.next;
        }

        ptrLT.next = notLessThan.next;
        ptrNLT.next = null;

        return lessThan.next;
        
    }
}
