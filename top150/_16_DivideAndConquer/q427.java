package top150._16_DivideAndConquer;

public class q427 {
    public Node construct(int[][] grid) {
        int n = grid.length;
        return helper(grid, n, 0, 0);
    }

    private Node helper(int[][] grid, int n, int x, int y) {
        if(!sameVal(grid, n, x, y)) {
            Node topLeft = helper(grid, n/2, x, y);
            Node topRight = helper(grid, n/2, x, y+n/2);
            Node btmLeft = helper(grid, n/2, x+n/2, y);
            Node btmRight = helper(grid, n/2, x+n/2, y+n/2);

            return new Node(false, false, topLeft, topRight, btmLeft, btmRight);
        } else {
            return new Node(grid[x][y] == 1? true:false, true);
        }
    }

    private boolean sameVal(int[][] grid, int n, int x, int y) {
        for(int i = x; i < x+n; i++) {
            for(int j = y; j < y+n; j++) {
                if(grid[i][j] != grid[x][y]) return false;
            }
        }

        return true;
    }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;
    
        
        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
        
        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
        
        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    };
}
