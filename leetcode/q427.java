package leetcode;

public class q427 {
    // Definition for a QuadTree node.
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
    }

    // BEST: revise check value function, making it early stop 
    class Solution {
        public Node construct(int[][] grid) {
            return construct(grid, grid.length, 0, 0);
        }
    
        public Node construct(int[][] grid, int n, int i, int j) {
            if(!sameVal(grid, n, i, j)) {
                Node topLeft = construct(grid, n/2, i, j);
                Node topRight = construct(grid, n/2, i, j+n/2);
                Node btmLeft = construct(grid, n/2, i+n/2, j);
                Node btmRight = construct(grid, n/2, i+n/2, j+n/2);
    
                return new Node(false, false, topLeft, topRight, btmLeft, btmRight);
            } else {
                return new Node(grid[i][j]==1?true:false, true);
            }
        }
    
        public boolean sameVal(int[][] grid, int n, int i, int j) {
            for(int ii = i; ii < i+n; ii++) {
                for(int jj = j; jj < j+n; jj++) {
                    if(grid[ii][jj] != grid[i][j]) return false;
                }
            }
            return true;
        }
    }

    // BETTER, using pointer to locate different blocks
    class SolutionBETTER {
        public Node construct(int[][] grid) {
            return construct(grid, grid.length, 0, 0);
        }
    
        public Node construct(int[][] grid, int n, int i, int j) {
            int val = blockVal(grid, n, i, j);
            if( val == -1) {
                Node topLeft = construct(grid, n/2, i, j);
                Node topRight = construct(grid, n/2, i, j+n/2);
                Node btmLeft = construct(grid, n/2, i+n/2, j);
                Node btmRight = construct(grid, n/2, i+n/2, j+n/2);
    
                return new Node(false, false, topLeft, topRight, btmLeft, btmRight);
            } else {
                return new Node(val==1?true:false, true);
            }
        }
    
        public int blockVal(int[][] grid, int n, int i, int j) {
            int count0 = 0;
            for(int ii = i; ii < i+n; ii++) {
                for(int jj = j; jj < j+n; jj++) {
                    if(grid[ii][jj] == 0) count0++;
                }
            }
    
            if(count0 == n*n) return 0;
            if(count0 == 0) return 1;
    
            return -1;
        }
    }

    // TOO SLOW:
    class SolutionSLOW {
        public Node construct(int[][] grid) {
            return construct(grid, grid.length);
        }

        public Node construct(int[][] grid, int n) {
            int val = blockVal(grid, n);
            if( val == -1) {
                int[][] topLeftBlock = new int[n/2][n/2];
                int[][] topRightBlock = new int[n/2][n/2];
                int[][] btmLeftBlock = new int[n/2][n/2];
                int[][] btmRightBlock = new int[n/2][n/2];
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < n; j++) {
                        if(i < n/2) {
                            //top
                            if(j < n/2) {
                                //left
                                topLeftBlock[i][j] = grid[i][j];
                            } else {
                                //right
                                topRightBlock[i][j-n/2] = grid[i][j];
                            }
                        } else {
                            //btm
                            if(j < n/2) {
                                //left
                                btmLeftBlock[i-n/2][j] = grid[i][j];
                            } else {
                                btmRightBlock[i-n/2][j-n/2] = grid[i][j];
                            }
                        }
                    }
                }

                Node topLeft = construct(topLeftBlock, n/2);
                Node topRight = construct(topRightBlock, n/2);
                Node btmLeft = construct(btmLeftBlock, n/2);
                Node btmRight = construct(btmRightBlock, n/2);

                return new Node(false, false, topLeft, topRight, btmLeft, btmRight);
            } else {
                return new Node(val==1?true:false, true);
            }
        }

        public int blockVal(int[][] grid, int n) {
            int count0 = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(grid[i][j] == 0) count0++;
                }
            }

            if(count0 == n*n) return 0;
            if(count0 == 0) return 1;

            return -1;
        }
    }
}
