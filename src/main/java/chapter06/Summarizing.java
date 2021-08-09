package chapter06;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;

import static chapter06.Dish.menu;

public class Summarizing {
    //
    public static void main(String[] args) {
        //

        long howManyDishes = menu.stream().collect(counting());
        System.out.println(howManyDishes);
        long howManyDishes2 = menu.stream().count();
        System.out.println(howManyDishes2);

        Comparator<Dish> dishCaloriesComparator =  Comparator.comparingInt(Dish::getCalories);

        //
        BinaryOperator<Dish> moreCalories = BinaryOperator.maxBy(dishCaloriesComparator);
        System.out.println(menu.stream().collect(Collectors.reducing(moreCalories)).get());

        //
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        //
        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        //
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);

//        String shortMenu2 = menu.stream().collect(joining());

        String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenu2);
    }

}
