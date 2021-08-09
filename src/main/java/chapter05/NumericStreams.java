package chapter05;

import chapter04.Dish;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static chapter04.Dish.menu;

public class NumericStreams {

    public static void main(String[] args) {
        //
        int calories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(calories);

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
        int max;
        if (maxCalories.isPresent()) {
            max = maxCalories.getAsInt();
        } else {
            max=0;
        }
        System.out.println(max);

        IntStream evenNumbers = IntStream.rangeClosed(1,100).filter(n->n%2==0);
        System.out.println(evenNumbers.count());

        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1,100)
                .boxed()
                .flatMap( a ->
                        IntStream.rangeClosed(1,100)
                                .filter(b->Math.sqrt(a*a + b*b) % 1== 0)
                                .mapToObj( b ->new int[]{a,b,(int)Math.sqrt(a*a+b*b)}));
        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        System.out.println();

        Stream<int[]> pythagoreanTriples2 = IntStream.rangeClosed(1,100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a,100)
                .mapToObj(b->new double[]{a,b,Math.sqrt(a*a+b*b)})
                .filter(t->t[2]%1==0))
                .map(array-> Arrays.stream(array).mapToInt(a->(int) a).toArray());
        pythagoreanTriples2.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
