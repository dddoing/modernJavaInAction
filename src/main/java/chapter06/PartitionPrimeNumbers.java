package chapter06;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class PartitionPrimeNumbers {
    //
    public static void main(String[] args) {
        //
        System.out.println(isPrime(100));
        System.out.println(isPrime2(100));
        System.out.println(partitionPrimes(100));
    }

    private static boolean isPrime(int candidate) {
        return IntStream.range(2,candidate).noneMatch(i-> candidate % i == 0);
    }

    public static boolean isPrime2(int candidate) {
        int candidateRoot = (int)Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2,candidateRoot).noneMatch(i->candidate%i ==0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        //
        return IntStream.rangeClosed(2,n).boxed().collect(partitioningBy(candidate->isPrime2(candidate)));
    }
}
