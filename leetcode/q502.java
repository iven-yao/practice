package leetcode;
import java.util.*;

public class q502 {
    class Project implements Comparable<Project> {
        int p;
        int c;

        public Project(int capital, int profit) {
            c = capital;
            p = profit;
        }

        public int compareTo(Project that) {
            return this.c - that.c;
        }

        public String toString() {
            return "["+c+","+p+"]";
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // greedy:
        // save time with priority-queue (max-heap)
        int n = capital.length;
        Project[] projs = new Project[n];
        for(int i = 0; i < n; i++) {
            projs[i] = new Project(capital[i], profits[i]);
        }

        Arrays.sort(projs);
        // for(int i = 0; i < capital.length; i++) {
        //     System.out.println(projs[i]);
        // }

        // System.out.println(cache);
        int projIndex = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        while(k > 0) {
            k--;

            while(projIndex < n && projs[projIndex].c <= w) {
                maxHeap.offer(projs[projIndex++].p);
            }

            if(maxHeap.size() == 0) {
                break;
            } else {
                w += maxHeap.poll();
            }

            // int todo = -1;
            // int maxP = 0;
            // find todo by getting highest profits, among enough capital projects:
            // obviously TLE, how to reduce
            // for(int i = 0; i < profits.length; i++) {
            //     if(w >= capital[i]) {
            //         maxP = Math.max(maxP, profits[i]);
            //         if(maxP == profits[i]) todo = i;
            //     }
            // }
            

            // if(todo == -1) break;
            // w += profits[todo];
            // profits[todo] = -1;
        }    

        return w;
    }

    // save some space complexity
    public int findMaximizedCapitalArray(int k, int w, int[] profits, int[] capital) {
        // greedy:
        // save time with priority-queue (max-heap)
        int n = capital.length;
        int[][] projs = new int[n][2];
        for(int i = 0; i < n; i++) {
            projs[i][0] = capital[i]; 
            projs[i][1] = profits[i];
        }

        Arrays.sort(projs, (a,b)-> a[0]-b[0]);
        // for(int i = 0; i < capital.length; i++) {
        //     System.out.println(projs[i]);
        // }
        int projIndex = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        while(k > 0) {
            k--;

            while(projIndex < n && projs[projIndex][0] <= w) {
                maxHeap.offer(projs[projIndex++][1]);
            }

            if(maxHeap.size() == 0) {
                break;
            } else {
                w += maxHeap.poll();
            }
        }    

        return w;
    }
}
