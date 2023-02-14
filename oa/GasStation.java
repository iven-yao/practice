package oa;
import java.lang.Math;

public class GasStation {

    public static int task1(int[] A, int X, int Y, int Z) {
        if(A.length == 0) return 0;
        int xx = 0,yy = 0,zz = 0;
        int maxWait = 0;
        for(int a: A) {
            System.out.print(a+":\t"+xx+",\t"+yy+",\t"+zz);
            if(a > X-xx && a > Y-yy && a > Z-zz) return -1;
            if((xx <= yy || a > Y-yy) && (xx <= zz || a > Z-zz) && a <= X-xx) {
                System.out.println("\t:to X");
                maxWait = Math.max(maxWait, xx);
                xx += a;
                continue;
            } 
            if((yy <= xx || a > X-xx) && (yy <= zz || a > Z-zz) && a <= Y-yy) {
                System.out.println("\t:to Y");
                maxWait = Math.max(maxWait, yy);
                yy+= a;
                continue;
            }
            if(a <= Z-zz) {
                System.out.println("\t:to Z");
                maxWait = Math.max(maxWait, zz);
                zz+= a;
                continue;
            }

            
        }
        
        return maxWait;
    }

    public static int task2(int A, int B) {
        int count = 0;

        for(int n = (int)Math.floor(Math.sqrt(A)); n < (int)Math.ceil(Math.sqrt(B)); n++) {
            if(n*(n+1) > B) break;
            if(n*(n+1) >= A) count++;
        }

        return count;
    }
    
    public static void main(String args[]) {
    //   int[] A = {2,8,4,3,2,1,1};
    //   System.out.println("task1 = " + task1(A,7,11,3));
      
    //   int[] B = {5};
    //   System.out.println("task1 = " + task1(B,4,0,3));
      
      int[] C = {123,245,331,23,142,234,1,35,99};
      System.out.println("task1 = " + task1(C,999,990,993));

      System.out.println("task2 = " + task2(6,20));
      System.out.println("task2 = " + task2(6,2000000000));

    }
}