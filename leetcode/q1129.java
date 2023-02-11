package leetcode;
// 1ms beat 100% :)
public class q1129 {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        
        int[] ans_rb = getShortestAB(n, redEdges, blueEdges);
        int[] ans_br = getShortestAB(n, blueEdges, redEdges);
        for(int i = 0; i < n; i++) {
            ans_rb[i] = Math.min(ans_rb[i], ans_br[i]);
            if(ans_rb[i] == Integer.MAX_VALUE) ans_rb[i] = -1;
        }
 
         return ans_rb;
     }
     
     public int[] getShortestAB(int n, int[][] a, int[][]b) {
         int[] ans_r = new int[n];
         int[] ans_b = new int[n];
         ans_r[0] = 0;
         ans_b[0] = 0;
         for(int i = 1; i < n; i++) {
             ans_r[i] = Integer.MAX_VALUE;
             ans_b[i] = Integer.MAX_VALUE;
         }
 
         boolean changed;
 
         // ABABABAB...
         do {
             changed = false;
             for(int[] edge: a) {
                 if(ans_b[edge[0]] != Integer.MAX_VALUE) {
                     if(ans_r[edge[1]] > ans_b[edge[0]]+1) {
                         changed = true;
                         ans_r[edge[1]] = ans_b[edge[0]]+1;
                     }
                 }
             }
 
             if(!changed) break;
             changed = false;
 
             for(int[] edge: b) {
                 if(ans_r[edge[0]] != Integer.MAX_VALUE) {
                     if(ans_b[edge[1]] > ans_r[edge[0]]+1) {
                         changed = true;
                         ans_b[edge[1]] = ans_r[edge[0]]+1;
                     }
                 }
             }
 
         } while(changed);
 
         for(int i = 0; i < n; i++) {
             ans_r[i] = Math.min(ans_r[i], ans_b[i]);
         }
 
         return ans_r;
     }
}
