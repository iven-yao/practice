package top150._13_GraphBFS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class q433 {
    public int minMutation(String startGene, String endGene, String[] bank) {
        if(!Arrays.asList(bank).contains(endGene)) return -1;

        // consider string as node, imagine there's an edge if only one char diff from two strings
        List<String> bankList = Arrays.asList(bank);
        HashSet<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();

        int steps = 0;
        q.offer(startGene);
        visited.add(startGene);

        // bfs
        while(!q.isEmpty()) {
            int size = q.size();
            // in one step, check everything currently in the queue
            for(int i = size; i > 0; i--) {
                String gene = q.poll();
                if(gene.equals(endGene)) return steps;

                // switch char one by one
                char[] cArr = gene.toCharArray();
                for(int j = 0; j < 8; j++) {
                    char c = cArr[j];

                    for(int k = 0; k < 4; k++) {
                        cArr[j] = "ACGT".charAt(k);
                        String nextGene = new String(cArr);

                        if(!visited.contains(nextGene) && bankList.contains(nextGene)) {
                            visited.add(nextGene);
                            q.offer(nextGene);
                        }
                    }
                    cArr[j] = c;
                }
            }
            steps++;
        }
        return -1;
    }
}
