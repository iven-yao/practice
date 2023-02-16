package leetcode;

import node_definition.ListNode;

public class q2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode ptr = dummy;
        int carry = 0;
        while(l1 != null || l2 != null || carry > 0) {
            int i1 = l1==null? 0: l1.val;
            int i2 = l2==null? 0: l2.val;
            int tmp = i1+i2+carry;
            ptr.next = new ListNode(tmp%10);
            carry = tmp/10;
            ptr = ptr.next;
            l1 = l1==null? null:l1.next;
            l2 = l2==null? null:l2.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        ListNode ans = new q2().addTwoNumbers(l1,l2);
        while(ans != null) {
            System.out.print(ans.val);
            ans = ans.next;
        }
        System.out.println();
        
    }
}
