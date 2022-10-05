import java.util.Arrays;

class q1996 {
    public int numberOfWeakCharacters(int[][] properties) {
        // Arrays.sort(properties, new Comparator<int[]>() {

        //     @Override
        //     public int compare(int[] o1, int[] o2) {
        //         if(o1[0] == o2[0]) {
        //             return o1[1] - o2[1];
        //         }
        //         return o2[0]-o1[0];
        //     }

        // });

        Arrays.sort(properties, (a, b) -> a[0]==b[0]? a[1]-b[1]: b[0]-a[0]);

        int defBar = 0;
        int weakCount = 0;
        for(int[] stat: properties) {
            if(stat[1] < defBar) {
                weakCount++;
            }

            defBar = Math.max(defBar, stat[1]);
        }

        return weakCount;
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{{1,2},{2,5},{2,4}};
        System.out.println(new q1996().numberOfWeakCharacters(test));
    }
}