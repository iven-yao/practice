package top150._12_GraphGeneral;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class q399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();

        for(int i = 0; i < equations.size(); i++) {
            double value = values[i];
            String dividend = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);

            map.putIfAbsent(dividend, new HashMap<>());
            map.putIfAbsent(divisor, new HashMap<>());

            map.get(dividend).put(divisor, value);
            map.get(divisor).put(dividend, 1.0/value);
        }

        double[] res = new double[queries.size()];
        for(int i = 0; i < queries.size(); i++) {
            double[] ans = {-1.0};
            if(map.containsKey(queries.get(i).get(0)) && map.containsKey(queries.get(i).get(1))) {
                Set<String> visited = new HashSet<>();
                dfs(queries.get(i).get(0), queries.get(i).get(1), map, visited, ans, 1.0);
            }
            res[i] = ans[0];
        }

        return res;
    }

    private void dfs(String dividend, String divisor, Map<String, Map<String, Double>> graph, Set<String> visited, double[] ans, double temp) {
        if(visited.contains(dividend)) return;
        visited.add(dividend);
        if(dividend.equals(divisor)) {
            ans[0] = temp;
            return;
        }

        for(Map.Entry<String, Double> entry: graph.get(dividend).entrySet()) {
            dfs(entry.getKey(), divisor, graph, visited, ans, temp*entry.getValue());
        }
    }
}
