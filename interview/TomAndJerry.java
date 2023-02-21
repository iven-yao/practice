package interview;

import java.util.*;

public class TomAndJerry {
    // giving a list of integer, Daily [2,5,6,15]
    // Daily[i] = n indicate different numbers(1 ~ n) tom and jerry could pick from at day i
    // each of them would like to pick a prime number that add up as another prime number less than n
    // find how many different pair tom and jerry could choose on every single day and return in a List

    public static List<Integer> dailyPairs(List<Integer> daily) {
        Integer[] ans = new Integer[daily.size()];
        for(int i = 0; i<daily.size(); i++) {
            ans[i] = 0;
        }

        // find max daily n
        int max = 0;
        for(Integer n : daily) {
            max = Math.max(max, n);
        }

        // build prime list and set
        List<Integer> primes = new ArrayList<>();
        Set<Integer> primesSet = new HashSet<>();        
        for(int i = 2; i <= max; i++) {
            if(isPrime(i)) {
                primes.add(i);
                primesSet.add(i);
            }
        }

        // calc ans
        for(int j = 1; j < primes.size(); j++) {
            int sum = 2+primes.get(j);
            if(primesSet.contains(sum)) {
                for(int day = 0; day < daily.size(); day++) {
                    if(sum <= daily.get(day)) {
                        ans[day]++;
                    }
                }
            }
        }
        

        return Arrays.asList(ans);
    }

    public static boolean isPrime(int a) {
        for(int i = 2; i <= a/2; i++) {
            if(a%i == 0) return false;
        }

        return true;
    }

    // TLE
    // Thoughts:
    // 1. sieve of Eratosthenes https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes 
    public static void main(String[] args) {
        System.out.println(dailyPairs(Arrays.asList(new Integer[]{500000})));
    }
}
