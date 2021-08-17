package chapter07;

import java.util.stream.Stream;

public class ParallelStreams {
    //
    public static void main(String[] args) {
        //
        System.out.println(sequentialSum(10));
        System.out.println(parallelSum(10));
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L,i->i+1).limit(n).reduce(0L,Long::sum);
//        long result =0;
//        for (long i = 0; i <=n; i++) {
//            result += i;
//        }
//        return result;
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L,i->i+1)
                .limit(n)
                .parallel()
                .reduce(0L,Long::sum);
    }
}
