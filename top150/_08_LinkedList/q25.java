package top150._08_LinkedList;

import node_definition.ListNode;

public class q25 {
    public int size(ListNode head){
        if(head==null){
            return 0;
        }
        if(head.next == null){
            return 1;
        }
         int p =0;
        ListNode current = head;
        while(current != null){
            current = current.next;
            p++;
        }
        return p; 
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return null;
        }
        if(head.next ==null){
            return head;
        }
        
        ListNode pre = null;
        ListNode curr = head;
        ListNode Next= null;
        int count = 0;
        while( curr!= null && count<k){
           Next = curr.next;
           curr.next = pre;
           pre=  curr;
           curr = Next;
           count++;
        }
        if(Next!=null && size(Next)>=k){
            head.next = reverseKGroup(Next,k);
        }
        else{
            head.next = Next;
        }
        return pre; 
    }
}
