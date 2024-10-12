package meta;

public class f12_l708 {
    // Class definition for a circular linked list node
    class Node {
        public int value;
        public Node next;

        // Constructor for creating a new node without next reference
        public Node(int value) {
            this.value = value;
        }

        // Constructor for creating a new node with next reference
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
    class Solution {
        public Node insert(Node head, int insertValue) {
            Node newNode = new Node(insertValue);

            if(head == null) {
                newNode.next = newNode;
                head = newNode;
            } else {
                Node previous = head;
                Node next = head.next;

                while(next != head && next.value < insertValue) {
                    next = next.next;
                    previous = previous.next;
                }

                previous.next = newNode;
                newNode.next = next;
            }

            return head;
        }
    }

    public static void print(Node head) {
        if(head == null){
            System.out.println();
            return;
        }

        Node ptr = head;

        do {
            System.out.print(ptr.value);
            System.out.print(",");
            ptr = ptr.next;
        } while(ptr!=head);

        System.out.println();
    }

    public static void main(String[] args) {
        Solution sol = new f12_l708().new Solution();

        Node test1 = null;
        test1 = sol.insert(test1, 0);
        print(test1);
        test1 = sol.insert(test1, 10);
        print(test1);
        test1 = sol.insert(test1, 50);
        print(test1);
        test1 = sol.insert(test1, 20);
        print(test1);
        test1 = sol.insert(test1, 40);
        print(test1);
        test1 = sol.insert(test1, 30);
        print(test1);

        
    }
}
