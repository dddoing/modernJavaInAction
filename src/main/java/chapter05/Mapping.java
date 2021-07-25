package chapter05;

import chapter04.Dish;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chapter04.Dish.menu;

public class Mapping {
    //
    public static void main(String[] args) {
        //
        List<String> menuName = menu.stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(menuName);

        System.out.println("================");
        List<String> words = Arrays.asList("Modern","Java","In","Action");
        List<Integer> wordLength = words.stream().map(String::length).collect(Collectors.toList());
        System.out.println(wordLength);

        System.out.println("Menu Length");
        wordLength = menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
        System.out.println(wordLength);

        System.out.println("String map");

        List<String> words1 = Arrays.asList("Hello","World");
        List<Stream<String>> a =words1.stream().map(word->word.split("")).map(Arrays::stream).distinct().collect(Collectors.toList());
        a.forEach(s->s.forEach(System.out::print));
        System.out.println();

        List<String> flatMap = words1.stream().map(word->word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(flatMap);

        //Quiz 5-2
        List<Integer> quiz1 = Arrays.asList(1,2,3,4,5);
        List<Integer> quiz1Answer = quiz1.stream().map(n->n*n).collect(Collectors.toList());
        System.out.println(quiz1Answer);

        List<Integer> quiz2Num1 = Arrays.asList(1,2,3);
        List<Integer> quiz2Num2 = Arrays.asList(4,5);
        List<int[]> quiz2Answer = quiz2Num1.stream().flatMap(i->quiz2Num2.stream().map(j->new int[]{i,j})).collect(Collectors.toList());
        quiz2Answer.forEach(num-> System.out.printf("(%s,%s)",num[0],num[1]));

        System.out.println();
        List<int[]> quiz3Answer = quiz2Num1.stream().flatMap(i->quiz2Num2.stream().filter(j->(i+j)%3==0).map(j->new int[]{i,j})).collect(Collectors.toList());
        quiz3Answer.forEach(num-> System.out.printf("(%s,%s)",num[0],num[1]));
    }
}
