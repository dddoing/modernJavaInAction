package chapter04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVSCollection {
    public static void main(String[] args) {
        //
        List<String> names = Arrays.asList("one","two","three","four");

        Stream<String> s = names.stream();
        s.forEach(System.out::println);
    }
}
