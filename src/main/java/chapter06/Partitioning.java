package chapter06;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

import static chapter06.Dish.menu;

public class Partitioning {

    public static void main(String[] args) {
        //
        System.out.println(partitioningMenu());
        List<Dish> vegetarianDishes = partitioningMenu().get(true);
        System.out.println(vegetarianDishes);
        System.out.println(vegetarianDishesByType());
        System.out.println(mostCaloricPartitionByVegetarian());

        Map<Boolean,Map<Boolean,List<Dish>>> quiz1 = menu.stream().collect(partitioningBy(Dish::isVegetarian,partitioningBy(dish -> dish.getCalories() > 500)));
        System.out.println(quiz1);

        //error: partitionBy는 불리언을 반환하는 함수, 즉 프레디케이트를 요구하므로 컴파일 되지 않음
//        Map<Boolean,Map<Boolean,List<Dish>>> quiz2 = menu.stream().collect(partitioningBy(Dish::isVegetarian,partitioningBy(Dish::getType)));
        Map<Boolean,Long> quiz3 = menu.stream().collect(partitioningBy(Dish::isVegetarian,counting()));
        System.out.println(quiz3);


    }

    private static Map<Boolean,List<Dish>> partitioningMenu() {
        //
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        //
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,groupingBy(Dish::getType)));
    }

    private static Map<Boolean, Dish> mostCaloricPartitionByVegetarian() {
        return menu.stream().collect(
                groupingBy(Dish::isVegetarian,collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get))
        );
    }

}
