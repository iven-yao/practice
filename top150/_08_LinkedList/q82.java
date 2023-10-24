package top150._08_LinkedList;

import node_definition.ListNode;

public class q82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ptr = head;
        ListNode res = new ListNode();
        ListNode resPtr = res;

        while(ptr != null) {
            
            ListNode ptr2 = ptr;
            while(ptr2 != null && ptr2.val == ptr.val) {
                ptr2 = ptr2.next;
            }

            if(ptr.next == ptr2) {
                resPtr.next = ptr;
                resPtr = resPtr.next;
            } 
            
            ptr = ptr2;
        }

        resPtr.next = null;
        return res.next;
    }
}
