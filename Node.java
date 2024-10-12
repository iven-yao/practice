class Node {
    private Node leftChild, rightChild;
    
    public Node(Node leftChild, Node rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    
    public Node getLeftChild() {
        return this.leftChild;
    }
    
    public Node getRightChild() {
        return this.rightChild;
    }

    public int height() {
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        int leftH = this.getLeftChild() == null? 0 : 1 + this.getLeftChild().height();
        int rightH = this.getRightChild() == null? 0 : 1 + this.getRightChild().height();
        
        return Math.max(leftH, rightH);
    }

    public static void main(String[] args) {
        Node leaf1 = new Node(null, null);
        Node leaf2 = new Node(null, null);
        Node node = new Node(leaf1, null);
        Node root = new Node(node, leaf2);

        System.out.println(root.height());
    }
}