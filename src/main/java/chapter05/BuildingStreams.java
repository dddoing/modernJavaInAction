package chapter05;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {
    //
    public static void main(String[] args) throws Exception{
        //
        Stream<String> stream = Stream.of("Modern","Java","In","Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();

        //
        int[] numbers = {2,3,5,7,11,13};
        System.out.println(Arrays.stream(numbers).sum());

        //
        Stream.iterate(0,n->n+2).limit(10).forEach(System.out::println);

        //
//        long uniqueWords = 0;
//        try(Stream<String> lines = Files.lines(Paths.get("data.txt"),Charset.defaultCharset())) {
//            uniqueWords = lines.flatMap(line->Arrays.stream(line.split(" "))).distinct().count();
//        }
//        System.out.println("There are " + uniqueWords + " unique words in data.txt");
        //
        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]}).limit(20).forEach(t-> System.out.println("{"+t[0]+","+t[1]+"}"));

        Stream.iterate(new int[]{0,1},t-> new int[]{t[1],t[0]+t[1]}).limit(10).map(t->t[0]).forEach(System.out::println);

        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        IntStream.generate(()->1).limit(5).forEach(System.out::println);

        IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        }).limit(5).forEach(System.out::println);

        System.out.println();
        IntSupplier fib = new IntSupplier() {

            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int nextValue = previous + current;
                previous = current;
                current = nextValue;
                return previous;
            }
        };

        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
