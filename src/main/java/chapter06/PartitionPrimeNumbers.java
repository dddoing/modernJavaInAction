package chapter06;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class PartitionPrimeNumbers {
    //
    public static void main(String[] args) {
        //
        System.out.println(isPrime(100));
        System.out.println(isPrime2(100));
        System.out.println(partitionPrimes(100));
        System.out.println(partitionPrime(100));
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

    public static Map<Boolean,List<Integer>> partitionPrime(int n) {
//        return IntStream.rangeClosed(2,n).boxed()
//                .collect(partitioningBy(candidate-> isPrime(candidate)));
        return IntStream.rangeClosed(2,n).boxed()
                .collect(new PrimeNumbersCollectors());
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return primes.stream()
                .takeWhile(i->i<=candidateRoot)
                .noneMatch(i-> candidate % i ==0);
//        return takeWhile(primes,i->i<=candidateRoot).stream().noneMatch(p->candidate%p==0);
    }

    public static <A> List <A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item: list) {
            if (!p.test(item)) {
                return list.subList(0,i);
            }
            i++;
        }
        return list;
    }

    public static class PrimeNumbersCollectors implements Collector<Integer,Map<Boolean,List<Integer>>,Map<Boolean,List<Integer>>> {
        //
        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return ()-> new HashMap<Boolean, List<Integer>>() {
                {
                    put(true,new ArrayList<>());
                    put(false,new ArrayList<>());
                }
            };
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (Map<Boolean,List<Integer>> acc,Integer candidate) -> {
                acc.get(isPrime(acc.get(true),candidate)).add(candidate);
            };
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (Map<Boolean,List<Integer>> map1, Map<Boolean,List<Integer>> map2) -> {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }
    }

}
