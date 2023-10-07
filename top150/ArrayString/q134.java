package top150.ArrayString;

public class q134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int overflow = 0;
        int totaloverflow = 0;
        int start = 0;

        for(int i = 0; i < n; i++) {
            overflow += gas[i]-cost[i];
            totaloverflow += gas[i]-cost[i];

            if(overflow < 0) {
                start = i+1;
                overflow = 0;
            }
        }

        return totaloverflow >= 0 ? start: -1;
    }
}
