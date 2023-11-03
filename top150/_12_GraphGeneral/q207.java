package top150._12_GraphGeneral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q207 {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        // build graph, key = course, value = list of prerequisites courses
        for(int i = 0; i < numCourses; i++) graph.put(i, new ArrayList<>());
        for(int[] pre: prerequisites) {
            graph.get(pre[0]).add(pre[1]);
        }

        boolean[] took = new boolean[numCourses];
        boolean[] checked = new boolean[numCourses];
        // traverse 0~numCourses, make sure there's a sequence to take all the courses
        for(int i = 0; i < numCourses; i++) {
            dfs(i, took, checked);
        }

        for(int i = 0; i < numCourses; i++) {
            if(!took[i]) return false;
        }

        return true;
    }

    private void dfs(int course, boolean[] took, boolean[] checked) {
        checked[course] = true;
        for(Integer pre: graph.get(course)){
            if(!checked[pre]) dfs(pre, took, checked);
            if(!took[pre]) return;
        }

        took[course] = true;
    }
}
